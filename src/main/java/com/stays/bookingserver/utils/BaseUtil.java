package com.stays.bookingserver.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.stays.bookingserver.converter.BookingConverter;
import com.stays.bookingserver.converter.BookingInfoConverter;
import com.stays.bookingserver.converter.BookingVsRoomConverter;
import com.stays.bookingserver.dao.BookingDAO;
import com.stays.bookingserver.dao.BookingInfoDAO;
import com.stays.bookingserver.dao.BookingVsRoomDAO;
import com.stays.bookingserver.helper.CashFreeApi;
import com.stays.bookingserver.service.GatewayService;
import com.stays.bookingserver.service.SacService;

public class BaseUtil {
	@Autowired
	protected BookingConverter bookingConverter;
	
	@Autowired
	protected BookingDAO bookingDAO;
	
	@Autowired
	protected BookingVsRoomConverter bookingVsRoomConverter;
	
	@Autowired
	protected SacService sacService;
	
	@Autowired
	protected BookingVsRoomDAO bookingVsRoomDAO;
	
	@Autowired
	protected BookingInfoConverter bookingInfoConverter;
	
	@Autowired
	protected BookingInfoDAO bookingInfoDAO;
	
	@Value("${generic.error.code}")
	protected String genericErrorCode;
	
	@Value("${generic.error.message}")
	protected String genericErrorMessage;
	
	@Autowired
	protected CashFreeApi cashFreeApi;
	
	@Autowired
	protected GatewayService gatewayService;
}
