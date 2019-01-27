package com.stays.bookingserver.serviceImpl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.stays.bookingserver.constants.BookingConstants;
import com.stays.bookingserver.entity.BookingEntity;
import com.stays.bookingserver.exceptions.FormExceptions;
import com.stays.bookingserver.model.BookingModel;
import com.stays.bookingserver.model.PaymentModel;
import com.stays.bookingserver.service.BookingService;

@Service
@Transactional
public class BookingServiceImpl extends BaseServiceImpl implements BookingService {
	private static final Logger logger = LogManager.getLogger(BookingServiceImpl.class);
	@Override
	public PaymentModel addBooking(BookingModel bookingModel) throws FormExceptions {
		logger.info("addBooking -- START");
		
		BookingEntity bookingEntity = bookingUtil.generateBookingEntity(bookingModel);
		
		PaymentModel paymentModel = null;
		//check payment mode
		if(bookingModel.getFormOfPayment().getMode().equalsIgnoreCase(BookingConstants.MODE_CASHLESS)) {
			//proceed with online payment
			paymentModel = bookingUtil.bookRoomForCashLessPayments(bookingModel, bookingEntity);
		}
		
		logger.info("addBooking -- END");
		return paymentModel;
	}

}
