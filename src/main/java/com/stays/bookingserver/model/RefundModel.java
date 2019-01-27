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
public class RefundModel {

	@JsonProperty("message")
	private String message;

	@JsonProperty("refundId")
	private String refundId;

	@JsonProperty("status")
	private String status;
}
