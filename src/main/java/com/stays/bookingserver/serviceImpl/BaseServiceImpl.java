package com.stays.bookingserver.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.stays.bookingserver.utils.BookingUtil;

public class BaseServiceImpl {
	@Autowired
	protected BookingUtil bookingUtil;
}
