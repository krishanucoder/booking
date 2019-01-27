package com.stays.bookingserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.stays.bookingserver.service.BookingService;

public class BookingBaseController {
	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;
	
	@Value("${common.success.code}")
	protected String commonSuccessCode;
	
	@Value("${common.success.message}")
	protected String commonSuccessMessage;
	
	@Value("${common.error.code}")
	protected String commonErrorCode;
	
	@Value("${common.error.message}")
	protected String commonErrorMessage;
	
	@Autowired
	protected BookingService bookingService;

}
