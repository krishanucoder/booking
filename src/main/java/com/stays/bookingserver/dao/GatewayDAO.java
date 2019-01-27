package com.stays.bookingserver.dao;

import org.springframework.stereotype.Repository;

import com.stays.bookingserver.entity.GatewayEntity;

@Repository
public class GatewayDAO extends GenericDAO<GatewayEntity, Long> {

	private static final long serialVersionUID = 9165628298943194582L;

	public GatewayDAO() {
		super(GatewayEntity.class);

	}
}
