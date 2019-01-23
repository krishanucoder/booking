package com.stays.bookingserver.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class BookingModel extends CommonModel {

	@JsonProperty("bookingId")
	private String bookingId;

	@JsonProperty("orabookingId")
	private String orabookingId;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("propertyId")
	private String propertyId;

	@JsonProperty("propertyLoc")
	private String propertyLoc;

	@JsonProperty("checkinDate")
	private String checkinDate;

	@JsonProperty("checkoutDate")
	private String checkoutDate;

	@JsonProperty("numOfDays")
	private String numOfDays;
	
	@JsonProperty("userFinalPrice")
	private String userFinalPrice;

	@JsonProperty("oraSpecialOfferPerc")
	private String oraSpecialOfferPerc;
	
	@JsonProperty("oraSpecialOfferAmt")
	private String oraSpecialOfferAmt;

	@JsonProperty("convenienceFeePerc")
	private String convenienceFeePerc;

	@JsonProperty("convenienceFeeAmt")
	private String convenienceFeeAmt;
	
	@JsonProperty("convenienceGstAmt")
	private String convenienceGstAmt;

	@JsonProperty("totalPrice")
	private String totalPrice;

	@JsonProperty("bookingInfos")
	private BookingInfoModel bookingInfoModel;

	@JsonProperty("bookingApproval")
	private String bookingApproval;
	
	@JsonProperty("convenienceAmtWgst")
	private String convenienceAmtWgst;
	
	@JsonProperty("bookingVsRooms")
	private List<BookingVsRoomModel> bookingVsRooms;
	
	@JsonProperty("bookingVsPayments")
	private List<BookingVsPaymentModel> bookingVsPaymentModels;
	
	@JsonProperty("formOfPayment")
	private FormOfPayment formOfPayment;
	
	@JsonProperty("cancellations")
	private CancellationModel cancellationModel;
	
	// Use to Call Booking Server Internally
	@JsonProperty("accomodationType")
	private String accomodationType;
}



