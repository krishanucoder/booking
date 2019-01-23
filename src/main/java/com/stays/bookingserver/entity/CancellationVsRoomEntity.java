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
@Table(name = "cancellation_vs_room")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CancellationVsRoomEntity extends CommonEntity {

	private static final long serialVersionUID = 5900218154144818309L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cancellation_vs_room_id")
	@JsonProperty("cancellationVsRoomId")
	private Long cancellationVsRoomId;
	
	@Column(name = "cancellation_slab_id")
	@JsonProperty("cancellationSlabId")
	private String cancellationSlabId;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "cancellation_id", nullable = false)
	@JsonProperty("cancellations")
	private CancellationEntity cancellationEntity;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "booking_vs_room_id", nullable = false)
	@JsonProperty("bookingVsRooms")
	private BookingVsRoomEntity bookingVsRoomEntity;
	
	
	/*cancellation_id
	booking_vs_room_id
	*/
	
	
	
	

	@Override
	public String toString() {
		return Long.toString(cancellationVsRoomId);
	}
}
