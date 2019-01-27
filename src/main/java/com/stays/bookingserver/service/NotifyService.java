package com.stays.bookingserver.service;

import java.util.Map;

public interface NotifyService {
	void updateBookingStatus(Map<String, String> paramMap);
}
