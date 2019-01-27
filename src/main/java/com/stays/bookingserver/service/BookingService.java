package com.stays.bookingserver.service;

import org.springframework.stereotype.Service;

import com.stays.bookingserver.exceptions.FormExceptions;
import com.stays.bookingserver.model.BookingModel;
import com.stays.bookingserver.model.PaymentModel;

@Service
public interface BookingService {
	PaymentModel addBooking(BookingModel bookingModel) throws FormExceptions;
}
