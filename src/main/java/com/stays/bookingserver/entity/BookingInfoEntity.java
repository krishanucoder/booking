package com.stays.bookingserver.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "booking_info")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookingInfoEntity extends CommonEntity {

	private static final long serialVersionUID = 1394133384784002535L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_info_id")
	@JsonProperty("bookingInfoId")
	private Long bookingInfoId;

	@Column(name = "name")
	@JsonProperty("name")
	private String name;

	@Column(name = "address")
	@JsonProperty("address")
	private String address;

	@Column(name = "company_name")
	@JsonProperty("companyName")
	private String companyName;

	@Column(name = "gstin")
	@JsonProperty("gstin")
	private String gstin;

	@Column(name = "mobile")
	@JsonProperty("mobile")
	private String mobile;

	@Column(name = "email")
	@JsonProperty("email")
	private String email;

	@Column(name = "checkin_pref_time")
	@JsonProperty("checkinPrefTime")
	private String checkinPrefTime;

	@Column(name = "checkout_pref_time")
	@JsonProperty("checkoutPrefTime")
	private String checkoutPrefTime;

	@Column(name = "identity_id")
	@JsonProperty("identityId")
	private String identityId;

	@Column(name = "id_file_url")
	@JsonProperty("idFileUrl")
	private String idFileUrl;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "booking_id", nullable = false)
	@JsonProperty("bookings")
	private BookingEntity bookingEntity;

	@Override
	public String toString() {
		return Long.toString(bookingInfoId);
	}
}
