package com.stays.bookingserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class BookingVsPaymentModel extends CommonModel {

	@JsonProperty("bookingVsPaymentId")
	private String bookingVsPaymentId;

	@JsonProperty("amountPaid")
	private String amountPaid;

	@JsonProperty("orderId")
	private String orderId;

	@JsonProperty("orderAmount")
	private String orderAmount;

	@JsonProperty("referenceId")
	private String referenceId;

	@JsonProperty("txStatus")
	private String txStatus;

	@JsonProperty("paymentMode")
	private String paymentMode;

	@JsonProperty("txMsg")
	private String txMsg;

	@JsonProperty("txTime")
	private String txTime;

	@JsonProperty("signature")
	private String signature;

	@JsonProperty("otherInfo")
	private String otherInfo;

	@JsonProperty("bookings")
	private BookingModel bookingModel;

	@JsonProperty("gateways")
	private GatewayModel gatewayModel;

}
