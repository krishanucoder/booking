package com.stays.bookingserver.service;

import com.stays.bookingserver.entity.GatewayEntity;

public interface GatewayService {
	GatewayEntity getGatewayEntity(String gatewayName);
}
