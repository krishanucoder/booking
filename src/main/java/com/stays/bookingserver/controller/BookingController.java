package com.stays.bookingserver.controller;

import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stays.bookingserver.constants.BookingConstants;
import com.stays.bookingserver.exceptions.FormExceptions;
import com.stays.bookingserver.helper.Util;
import com.stays.bookingserver.model.BookingModel;
import com.stays.bookingserver.model.PaymentModel;
import com.stays.bookingserver.model.ResponseModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "Booking", tags = "Booking")
public class BookingController extends BookingBaseController {
	private static final Logger logger = LogManager.getLogger(BookingController.class);

	@PostMapping(value = "/add-booking", produces = "application/json")
	@ApiOperation(value = "Add Booking", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!") })

	public ResponseEntity<ResponseModel> addBooking(@RequestBody BookingModel bookingModel) {

		logger.info("addBooking -- START");

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(bookingModel, BookingConstants.INCOMING, "add-booking", request);
		try {
			PaymentModel paymentModel = bookingService.addBooking(bookingModel);
			responseModel.setResponseBody(paymentModel);
			responseModel.setResponseCode(commonSuccessCode);
			responseModel.setResponseMessage(commonSuccessMessage);
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in add-booking -- {}", Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Add Booking -- {}", Util.errorToString(e));
			}
			responseModel.setResponseCode(commonErrorCode);
			responseModel.setResponseMessage(commonErrorMessage);
		}

		Util.printLog(responseModel, BookingConstants.OUTGOING, "add-booking", request);

		logger.info("addBooking -- END");

		if (responseModel.getResponseCode().equals(commonSuccessCode)) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}
