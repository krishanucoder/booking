package com.stays.bookingserver.dao;

import org.springframework.stereotype.Repository;

import com.stays.bookingserver.entity.BookingVsRoomEntity;

@Repository
public class BookingVsRoomDAO extends GenericDAO<BookingVsRoomEntity, Long> {

	private static final long serialVersionUID = 7373009496614785594L;

	public BookingVsRoomDAO() {
		super(BookingVsRoomEntity.class);

	}
}
