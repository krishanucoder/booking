package com.stays.bookingserver.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "booking_vs_payment")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookingVsPaymentEntity extends CommonEntity {

	private static final long serialVersionUID = -3411930437094752976L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_vs_payment_id")
	@JsonProperty("bookingVsPaymentId")
	private Long bookingVsPaymentId;

	@Column(name = "amount_paid")
	@JsonProperty("amountPaid")
	private String amountPaid;

	@Column(name = "orderId")
	@JsonProperty("orderId")
	private String orderId;

	@Column(name = "orderAmount")
	@JsonProperty("orderAmount")
	private String orderAmount;

	@Column(name = "referenceId")
	@JsonProperty("referenceId")
	private String referenceId;

	@Column(name = "txStatus")
	@JsonProperty("txStatus")
	private String txStatus;

	@Column(name = "paymentMode")
	@JsonProperty("paymentMode")
	private String paymentMode;

	@Column(name = "txMsg")
	@JsonProperty("txMsg")
	private String txMsg;

	@Column(name = "txTime")
	@JsonProperty("txTime")
	private String txTime;

	@Column(name = "signature")
	@JsonProperty("signature")
	private String signature;

	@Column(name = "other_info")
	@JsonProperty("otherInfo")
	private String otherInfo;

	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "booking_id", nullable = false)
	@JsonProperty("bookings")
	private BookingEntity bookingEntity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "gateway_id", nullable = false)
	@JsonProperty("gateways")
	private GatewayEntity gatewayEntity;

	@Column(name = "percentage")
	@JsonProperty("percentage")
	private String percentage;

	
	@Override
	public String toString() {
		return Long.toString(bookingVsPaymentId);
	}
}