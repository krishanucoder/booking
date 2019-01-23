package com.stays.bookingserver.model;

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
public class BookingVsRoomModel extends CommonModel {

	@JsonProperty("bookingVsRoomId")
	private String bookingVsRoomId;

	@JsonProperty("oraRoomName")
	private String oraRoomName;
	
	@JsonProperty("roomId")
	private String roomId;
	
	@JsonProperty("hostBasePrice")
	private String hostBasePrice;

	@JsonProperty("hostDiscount")
	private String hostDiscount;

	@JsonProperty("hostPrice")
	private String hostPrice;
	
	@JsonProperty("oraMarkUp")
	private String oraMarkUp;

	@JsonProperty("oraPrice")
	private String oraPrice;

	@JsonProperty("oraDiscount")
	private String oraDiscount;
	
	@JsonProperty("oraFinalPrice")
	private String oraFinalPrice;
	
	@JsonProperty("sgst")
	private String sgst;

	@JsonProperty("cgst")
	private String cgst;

	@JsonProperty("igst")
	private String igst;
	
	@JsonProperty("gstAmt")
	private String gstAmt;
	
	@JsonProperty("totalAmt")
	private String totalAmt;

	@JsonProperty("numOfAdult")
	private String numOfAdult;
	
	@JsonProperty("numOfChild")
	private String numOfChild;

	@JsonProperty("numOfCot")
	private String numOfCot;
	
	@JsonProperty("bookings")
	private BookingModel bookingModel;

	@JsonProperty("sacCodes")
	private SacCodeModel sacCodeModel;

	@JsonProperty("numOfSharedBed")
	private String numOfSharedBed;
	
	@JsonProperty("numOfSharedCot")
	private String numOfSharedCot;
	
	@JsonProperty("totalNumOfSharedCot")
	private String totalNumOfSharedCot;
	
	@JsonProperty("totalNumOfSharedBed")
	private String totalNumOfSharedBed;
	
	@JsonProperty("cancellationVsRooms")
	private CancellationVsRoomModel cancellationVsRoomModel;
	
	@JsonProperty("roomVsOfferId")
	private String roomVsOfferId;
	
	
}
