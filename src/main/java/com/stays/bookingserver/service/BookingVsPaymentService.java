package com.stays.bookingserver.service;

import com.stays.bookingserver.entity.BookingVsPaymentEntity;

public interface BookingVsPaymentService {
	BookingVsPaymentEntity getBookingVsPaymentEntityByOrderId(String orderId);
}
