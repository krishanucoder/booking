package com.stays.bookingserver.helper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.stays.bookingserver.constants.AccommodationType;
import com.stays.bookingserver.constants.BookingConstants;
import com.stays.bookingserver.constants.BookingStatus;
import com.stays.bookingserver.constants.PaymentStatus;
import com.stays.bookingserver.constants.RoomStatus;
import com.stays.bookingserver.constants.Status;
import com.stays.bookingserver.dao.BookingDAO;
import com.stays.bookingserver.dao.BookingVsPaymentDAO;
import com.stays.bookingserver.dao.BookingVsRoomDAO;
import com.stays.bookingserver.dao.CancellationDAO;
import com.stays.bookingserver.dao.CancellationVsRoomDAO;
import com.stays.bookingserver.entity.BookingEntity;
import com.stays.bookingserver.entity.BookingVsPaymentEntity;
import com.stays.bookingserver.entity.BookingVsRoomEntity;
import com.stays.bookingserver.entity.CancellationEntity;
import com.stays.bookingserver.entity.CancellationVsRoomEntity;
import com.stays.bookingserver.exceptions.FormExceptions;
import com.stays.bookingserver.model.RefundModel;
import com.stays.bookingserver.service.BookingVsPaymentService;
import com.stays.bookingserver.service.BookingVsRoomService;
import com.stays.bookingserver.serviceImpl.NotifyServiceImpl;

@Component
public class RoomUpdateAfterGatewayPayment {
	private static final Logger logger = LogManager.getLogger(NotifyServiceImpl.class);

	@Autowired
	protected BookingVsPaymentService bookingVsPaymentService;

	@Autowired
	protected BookingDAO bookingDAO;

	@Autowired
	protected BookingVsRoomDAO bookingVsRoomDAO;

	@Autowired
	protected BookingVsPaymentDAO bookingVsPaymentDAO;

	@Autowired
	protected BookingVsRoomService bookingVsRoomService;

	@Autowired
	protected CashFreeApi cashFreeApi;

	@Autowired
	protected CancellationDAO cancellationDAO;

	@Autowired
	protected CancellationVsRoomDAO cancellationVsRoomDAO;

	public synchronized String checkRoomStatusAndBookOrRefund(Map<String, String> paramMap) {
		logger.info("checkRoomStatusAndBookOrRefund -- START");
		
		List<BookingVsRoomEntity> bookingVsRoomEntities = new CopyOnWriteArrayList<>();

		BookingVsPaymentEntity bookingVsPaymentEntity = bookingVsPaymentService
				.getBookingVsPaymentEntityByOrderId(paramMap.get(BookingConstants.orderId));

		bookingVsPaymentEntity.setTxStatus(paramMap.get(BookingConstants.txStatus));
		bookingVsPaymentEntity.setReferenceId(paramMap.get(BookingConstants.referenceId));
		bookingVsPaymentEntity.setPaymentMode(paramMap.get(BookingConstants.paymentMode));
		bookingVsPaymentEntity.setTxTime(paramMap.get(BookingConstants.txTime));
		bookingVsPaymentEntity.setTxMsg(paramMap.get(BookingConstants.txMsg));
		bookingVsPaymentEntity.setSignature(paramMap.get(BookingConstants.signature));
		bookingVsPaymentEntity.setModifiedBy(bookingVsPaymentEntity.getCreatedBy());
		bookingVsPaymentEntity.setModifiedDate(Util.getCurrentDateTime());

		// bookingVsPaymentEntity.setStatus(PaymentStatus.CANCELLED.ordinal()); set
		// status as cancelled if room booked.
		// txstatus will be success but status will be cancelled and refund will be
		// initiated

		// get the booking entity
		BookingEntity bookingEntity = bookingDAO.find(bookingVsPaymentEntity.getBookingEntity().getBookingId());

		// check room status in a synchronized way for private and shared
		bookingEntity.getBookingVsRoomEntities().parallelStream().forEach(room -> {
			// for private room check numberOfCot exists or not for that booking
			// for shared room call property server

			if (bookingEntity.getAccomodationType().equalsIgnoreCase(AccommodationType.PRIVATE.name())) { // private
																											// room
				if (bookingVsRoomService.checkIfPrivateRoomIsBooked(room.getRoomId())) {
					// room is booked
					bookingVsRoomEntities.add(room);

				}
			} else { // shared room call property server

			}
		});

		if (bookingVsRoomEntities.size() > 0) {
			// initiate refund and set cash part to cancelled
			initiateRefund(bookingEntity, bookingVsPaymentEntity);
		} else {
			if (Double.parseDouble(bookingVsPaymentEntity.getOrderAmount()) == Double
					.parseDouble((paramMap.get("orderAmount")))) {
				// update room
				bookingEntity.getBookingVsRoomEntities().forEach(room -> {
					// update room
					room.setModifiedBy(room.getCreatedBy());
					room.setModifiedDate(Util.getCurrentDateTime());
					room.setStatus(RoomStatus.BOOKED.ordinal());

				});

				// update booking vs payment
				bookingVsPaymentEntity.setModifiedBy(bookingVsPaymentEntity.getCreatedBy());
				bookingVsPaymentEntity.setModifiedDate(Util.getCurrentDateTime());
				bookingVsPaymentEntity.setStatus(PaymentStatus.COMPLETED.ordinal());

				bookingVsPaymentDAO.update(bookingVsPaymentEntity);

				// update booking master
				bookingEntity.setModifiedBy(bookingEntity.getCreatedBy());
				bookingEntity.setModifiedDate(Util.getCurrentDateTime());
				bookingEntity.setStatus(BookingStatus.BOOKED.ordinal());

				bookingDAO.update(bookingEntity);
			} else {
				logger.info("customer changed the order amount");
				
			}
		}
		logger.info("checkRoomStatusAndBookOrRefund -- END");
		return bookingEntity.getSuccessURL();
	}

	// initiate refund and update booking master, bookingVsRoom, bookingVsPayment
	// and cancellation
	public void initiateRefund(BookingEntity bookingEntity, BookingVsPaymentEntity bookingVsPaymentEntity) {
		bookingVsPaymentEntity.setModifiedBy(bookingVsPaymentEntity.getCreatedBy());
		bookingVsPaymentEntity.setModifiedDate(Util.getCurrentDateTime());
		bookingVsPaymentEntity.setStatus(PaymentStatus.CANCELLED.ordinal());

		try {
			RefundModel refundModel = cashFreeApi.initiateRefund(bookingEntity, bookingVsPaymentEntity,
					bookingVsPaymentEntity.getOrderAmount(), BookingConstants.REFUND_CONCURRENT_BOOKING_NOTE);
			if (refundModel.getStatus().equalsIgnoreCase(BookingConstants.CASHFREE_OK)) {

				CancellationEntity cancellationEntity = new CancellationEntity();
				cancellationEntity.setCreatedBy(bookingEntity.getCreatedBy());
				cancellationEntity.setCreatedDate(Util.getCurrentDateTime());
				cancellationEntity.setStatus(Status.INACTIVE.ordinal());
				cancellationEntity.setReasonForCancellation(BookingConstants.REFUND_CONCURRENT_BOOKING_NOTE);
				cancellationEntity.setTotalAmountPaid(bookingVsPaymentEntity.getOrderAmount());
				cancellationEntity.setTotalAmountRefunded(bookingVsPaymentEntity.getAmountPaid());
				// cancellationEntity.setTotalPaybleWithoutGst(bookingEntity.getTotalPaybleWithoutGST());
				cancellationEntity.setBookingEntity(bookingEntity);
				cancellationEntity.setUserId(String.valueOf(bookingVsPaymentEntity.getCreatedBy()));

				Long id = (Long) cancellationDAO.save(cancellationEntity);
				CancellationEntity cancellationEntity2 = cancellationDAO.find(id);

				bookingEntity.getBookingVsRoomEntities().parallelStream().forEach(room -> {
					room.setModifiedBy(room.getCreatedBy());
					room.setModifiedDate(Util.getCurrentDateTime());
					room.setStatus(RoomStatus.INACTIVE.ordinal());
					bookingVsRoomDAO.update(room);

					// insert into cancellation vs room
					CancellationVsRoomEntity cancellationVsRoomEntity = new CancellationVsRoomEntity();
					cancellationVsRoomEntity.setCreatedBy(bookingEntity.getCreatedBy());
					cancellationVsRoomEntity.setCreatedDate(Util.getCurrentDateTime());
					cancellationVsRoomEntity.setStatus(Status.INACTIVE.ordinal());
					cancellationVsRoomEntity.setBookingVsRoomEntity(room);
					cancellationVsRoomEntity.setCancellationEntity(cancellationEntity2);

					cancellationVsRoomDAO.save(cancellationVsRoomEntity);
				});

				// update booking entity
				bookingEntity.setModifiedBy(bookingEntity.getCreatedBy());
				bookingEntity.setModifiedDate(Util.getCurrentDateTime());
				bookingEntity.setStatus(BookingStatus.CANCELLED.ordinal());
				bookingDAO.update(bookingEntity);

				// update booking vs payment entity
				bookingVsPaymentDAO.update(bookingVsPaymentEntity);

				bookingEntity.getBookingVsPaymentEntities().forEach(bpe -> {
					if (bpe.getGatewayEntity().getGatewayId() != bookingVsPaymentEntity.getGatewayEntity()
							.getGatewayId()) {
						// cash payment checking

						bpe.setModifiedBy(bpe.getCreatedBy());
						bpe.setModifiedDate(Util.getCurrentDateTime());
						bpe.setStatus(PaymentStatus.CANCELLED.ordinal());
						bookingVsPaymentDAO.update(bpe);
					}
				});
				// insert into cancellation
			} else {
				// refund not done
			}
		} catch (FormExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
