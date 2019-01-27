package com.stays.bookingserver.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.stays.bookingserver.constants.BookingConstants;
import com.stays.bookingserver.helper.Util;
import com.stays.bookingserver.service.NotifyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "Notify Booking", tags = "Notify Booking")
public class NotifyController {
	private static final Logger logger = LogManager.getLogger(BookingController.class);

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	@Autowired
	protected NotifyService notifyService;

	@RequestMapping(value = "/notify-booking", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	@ApiOperation(value = "Notify Booking Cashfree")
	public ModelAndView notifyPayment(@RequestParam Map<String, String> paramMap) {
		logger.info("notifyPayment -- START");
		
		Util.printLog(paramMap, BookingConstants.INCOMING, "notify-booking", request);
		String returnUrl = notifyService.updateBookingStatus(paramMap);
		
		logger.info("notifyPayment -- END");
		
		return new ModelAndView(new RedirectView(returnUrl));
	}
}
