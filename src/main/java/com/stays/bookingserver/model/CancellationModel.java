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
public class CancellationModel extends CommonModel {

	@JsonProperty("cancellationId")
	private Long cancellationId;

	@JsonProperty("totalPaybleWithoutGst")
	private String totalPaybleWithoutGst;

	@JsonProperty("totalAmountPaid")
	private String totalAmountPaid;

	@JsonProperty("userId")
	private String userId;

	@JsonProperty("reasonForCancellation")
	private String reasonForCancellation;

	@JsonProperty("totalAmountRefunded")
	private String totalAmountRefunded;

	@JsonProperty("bookings")
	private BookingModel bookingModel;
	
	@JsonProperty("cancellationVsRooms")
	private List<CancellationVsRoomModel> cancellationVsRoomsModels;

}



