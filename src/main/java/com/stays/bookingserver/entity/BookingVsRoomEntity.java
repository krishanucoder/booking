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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "booking_vs_room")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookingVsRoomEntity extends CommonEntity {

	private static final long serialVersionUID = -8084405527617612386L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_vs_room_id")
	@JsonProperty("bookingVsRoomId")
	private Long bookingVsRoomId;
	
	@Column(name = "ora_room_name")
	@JsonProperty("oraRoomName")
	private String oraRoomName;
	
	@Column(name = "room_id")
	@JsonProperty("roomId")
	private String roomId;

	@Column(name = "host_base_price")
	@JsonProperty("hostBasePrice")
	private String hostBasePrice;

	@Column(name = "host_discount")
	@JsonProperty("hostDiscount")
	private String hostDiscount;

	@Column(name = "host_price")
	@JsonProperty("hostPrice")
	private String hostPrice;
	
	@Column(name = "ora_mark_up")
	@JsonProperty("oraMarkUp")
	private String oraMarkUp;

	@Column(name = "ora_price")
	@JsonProperty("oraPrice")
	private String oraPrice;

	@Column(name = "ora_discount")
	@JsonProperty("oraDiscount")
	private String oraDiscount;
	
	@Column(name = "ora_final_price")
	@JsonProperty("oraFinalPrice")
	private String oraFinalPrice;
	
	@Column(name = "sgst")
	@JsonProperty("sgst")
	private String sgst;

	@Column(name = "cgst")
	@JsonProperty("cgst")
	private String cgst;

	@Column(name = "igst")
	@JsonProperty("igst")
	private String igst;
	
	@Column(name = "gst_amt")
	@JsonProperty("gstAmt")
	private String gstAmt;
	
	@Column(name = "total_amt")
	@JsonProperty("totalAmt")
	private String totalAmt;

	@Column(name = "num_of_adult")
	@JsonProperty("numOfAdult")
	private String numOfAdult;
	
	@Column(name = "num_of_child")
	@JsonProperty("numOfChild")
	private String numOfChild;

	@Column(name = "num_of_cot")
	@JsonProperty("numOfCot")
	private String numOfCot;
	
	@Column(name = "num_of_shared_bed")
	@JsonProperty("numOfSharedBed")
	private String numOfSharedBed;
	
	@Column(name = "num_of_shared_cot")
	@JsonProperty("numOfSharedCot")
	private String numOfSharedCot;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "booking_id", nullable = false)
	@JsonProperty("bookings")
	private BookingEntity bookingEntity;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "sac_code_id", nullable = false)
	@JsonProperty("sacCodes")
	private SacCodeEntity sacCodeEntity;

	@Column(name = "room_vs_offer_id")
	@JsonProperty("roomVsOfferId")
	private String roomVsOfferId;
	
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "bookingVsRoomEntity", cascade = { CascadeType.ALL })
	@JsonProperty("cancellationVsRooms")
	private CancellationVsRoomEntity cancellationVsRoomEntity;

	
	@Override
	public String toString() {
		return Long.toString(bookingVsRoomId);
	}
}
