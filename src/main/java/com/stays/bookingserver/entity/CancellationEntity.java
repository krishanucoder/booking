package com.stays.bookingserver.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_cancellation")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CancellationEntity extends CommonEntity {


	private static final long serialVersionUID = -2727104813693781941L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cancellation_id")
	@JsonProperty("cancellationId")
	private Long cancellationId;

	@Column(name = "total_payble_without_gst")
	@JsonProperty("totalPaybleWithoutGst")
	private String totalPaybleWithoutGst;

	@Column(name = "total_amount_paid")
	@JsonProperty("totalAmountPaid")
	private String totalAmountPaid;

	@Column(name = "user_id")
	@JsonProperty("userId")
	private String userId;

	@Column(name = "reason_for_cancellation")
	@JsonProperty("reasonForCancellation")
	private String reasonForCancellation;

	@Column(name = "total_amount_refunded")
	@JsonProperty("totalAmountRefunded")
	private String totalAmountRefunded;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "booking_id", nullable = false)
	@JsonProperty("bookings")
	private BookingEntity bookingEntity;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cancellationEntity", cascade = { CascadeType.ALL })
	@JsonProperty("cancellationVsRooms")
	private List<CancellationVsRoomEntity> cancellationVsRoomsEntities;

	@Override
	public String toString() {
		return Long.toString(cancellationId);
	}
}