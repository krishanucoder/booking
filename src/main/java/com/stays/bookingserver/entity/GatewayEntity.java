package com.stays.bookingserver.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_gateway")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class GatewayEntity extends CommonEntity {

	private static final long serialVersionUID = -8584098158447082108L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gateway_id")
	@JsonProperty("gatewayId")
	private Long gatewayId;

	@Column(name = "gateway_name")
	@JsonProperty("gatewayName")
	private String gatewayName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gatewayEntity", cascade = { CascadeType.ALL })
	@JsonProperty("bookingVsPayments")
	private List<BookingVsPaymentEntity> bookingVsPaymentEntities;

	@Override
	public String toString() {
		return Long.toString(gatewayId);
	}
}