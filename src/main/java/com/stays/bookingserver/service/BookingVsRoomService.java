package com.stays.bookingserver.service;

public interface BookingVsRoomService {
	boolean checkIfPrivateRoomIsBooked(String roomId);
	public boolean checkIfSharedRoomIsBooked(String roomId);}
