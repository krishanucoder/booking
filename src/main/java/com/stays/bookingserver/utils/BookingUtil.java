package com.stays.bookingserver.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.stays.bookingserver.constants.BookingConstants;
import com.stays.bookingserver.constants.BookingStatus;
import com.stays.bookingserver.constants.PaymentStatus;
import com.stays.bookingserver.constants.RoomStatus;
import com.stays.bookingserver.constants.Status;
import com.stays.bookingserver.entity.BookingEntity;
import com.stays.bookingserver.entity.BookingInfoEntity;
import com.stays.bookingserver.entity.BookingVsPaymentEntity;
import com.stays.bookingserver.entity.BookingVsRoomEntity;
import com.stays.bookingserver.entity.GatewayEntity;
import com.stays.bookingserver.exceptions.FormExceptions;
import com.stays.bookingserver.helper.Util;
import com.stays.bookingserver.model.BookingModel;
import com.stays.bookingserver.model.PaymentModel;

@Component
public class BookingUtil extends BaseUtil {
	private static final Logger logger = LogManager.getLogger(BookingUtil.class);

	public BookingEntity generateBookingEntity(BookingModel bookingModel) throws FormExceptions {
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		try {
			BookingEntity bookingEntity = bookingConverter.modelToEntity(bookingModel);
			// set booking master attributes
			bookingEntity.setOrabookingId("ORA" + new Date().getTime());
			bookingEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
			bookingEntity.setCreatedDate(Util.getCurrentDateTime());
			bookingEntity.setStatus(BookingStatus.INACTIVE.ordinal());

			Long bookingId = (Long) bookingDAO.save(bookingEntity);
			BookingEntity bookingEntity2 = bookingDAO.find(bookingId);

			List<BookingVsRoomEntity> bookingVsRoomEntities = new ArrayList<>();

			bookingModel.getBookingVsRoomModels().forEach(room -> {
				BookingVsRoomEntity bookingVsRoomEntity = bookingVsRoomConverter.modelToEntity(room);

				bookingVsRoomEntity.setStatus(RoomStatus.INACTIVE.ordinal());
				bookingVsRoomEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
				bookingVsRoomEntity.setCreatedDate(Util.getCurrentDateTime());
				bookingVsRoomEntity.setSacCodeEntity(sacService.getActiveSacCodeEntity());
				bookingVsRoomEntity.setBookingEntity(bookingEntity2);

				bookingVsRoomDAO.save(bookingVsRoomEntity);
				bookingVsRoomEntities.add(bookingVsRoomEntity);
			});

			BookingInfoEntity bookingInfoEntity = bookingInfoConverter
					.modelToEntity(bookingModel.getBookingInfoModel());
			bookingInfoEntity.setCreatedDate(Util.getCurrentDateTime());
			bookingInfoEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
			bookingInfoEntity.setStatus(Status.ACTIVE.ordinal());
			bookingInfoEntity.setBookingEntity(bookingEntity2);

			bookingInfoDAO.save(bookingInfoEntity);

			bookingEntity2.setBookingInfoEntity(bookingInfoEntity);
			bookingEntity2.setBookingVsRoomEntities(bookingVsRoomEntities);
			return bookingEntity2;
		} catch (Exception e) {
			// throw new FormExceptions(exceptions)
			e.printStackTrace();
			exceptions.put(genericErrorCode, new Exception(genericErrorMessage));
			throw new FormExceptions(exceptions);
		}
	}

	public PaymentModel bookRoomForCashLessPayments(BookingModel bm, BookingEntity be) throws FormExceptions {

		logger.debug("bookRoomForCashLessPayments -- Start");

		BookingVsPaymentEntity bookingVsPaymentEntity = new BookingVsPaymentEntity();
		bookingVsPaymentEntity.setBookingEntity(be);
		bookingVsPaymentEntity.setCreatedBy(Long.parseLong(bm.getUserId()));
		bookingVsPaymentEntity.setCreatedDate(Util.getCurrentDateTime());
		bookingVsPaymentEntity.setOrderId("ORA_TRNS" + new Date().getTime());
		bookingVsPaymentEntity.setPercentage(Util.roundTo2Places(100.00)); // remove hardcode
		GatewayEntity gatewayEntity = gatewayService.getGatewayEntity(BookingConstants.CASHFREE);
		bookingVsPaymentEntity.setGatewayEntity(gatewayEntity);
		bookingVsPaymentEntity.setOrderAmount(be.getUserFinalPrice());
		bookingVsPaymentEntity.setAmountPaid(String.valueOf(Status.ZERO.ordinal()));
		bookingVsPaymentEntity.setStatus(PaymentStatus.ACTIVE.ordinal());

		PaymentModel paymentModel = cashFreeApi.getPaymentLink(bm, be, bookingVsPaymentEntity);

		logger.debug("bookRoomForCashLessPayments -- End");
		return paymentModel;
	}
}
