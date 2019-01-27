package com.stays.bookingserver.dao;

import org.springframework.stereotype.Repository;

import com.stays.bookingserver.entity.CancellationVsRoomEntity;

@Repository
public class CancellationVsRoomDAO extends GenericDAO<CancellationVsRoomEntity, Long> {

	private static final long serialVersionUID = 3031861414828442518L;

	public CancellationVsRoomDAO() {
		super(CancellationVsRoomEntity.class);

	}
}