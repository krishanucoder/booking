package com.stays.bookingserver.constants;

public interface BookingConstants {
	public static final String INCOMING = "Incoming";
	public static final String OUTGOING = "Outgoing";

	public static final String MODE_CASH = "cash";
	public static final String MODE_CASHLESS = "cashless";
	public static final String MODE_PARTIAL = "partial";

	// cashfree constants
	public static final String appId = "appId";
	public static final String secretKey = "secretKey";
	public static final String orderId = "orderId";
	public static final String orderAmount = "orderAmount";
	public static final String orderCurrency = "orderCurrency";
	public static final String customerEmail = "customerEmail";
	public static final String customerName = "customerName";
	public static final String customerPhone = "customerPhone";
	public static final String returnUrl = "returnUrl";
	public static final String notifyUrl = "notifyUrl";
	
	public static final String txStatus = "txStatus";
	public static final String referenceId = "referenceId";
	public static final String paymentMode = "paymentMode";
	public static final String txTime = "txTime";
	public static final String txMsg = "txMsg";
	public static final String signature = "signature";

	// cashfreeapi status
	public static final String CASHFREE_OK = "OK";
	public static final String CASHFREE_ERROR = "ERROR";

	// gateways
	public static final String CASHFREE = "cashfree";

	// refund note during concurrency
	public static final String REFUND_CONCURRENT_BOOKING_NOTE = "Rooms are already booked. Refunding the booking amount.";
	public static final String USER_CANCELLED_BOOKING_IN_GATEWAY = "User cancelled booking in payment gateway";
	
	
	//payment status cashfree
	public static final String SUCCESS = "SUCCESS";
	public static final String FLAGGED = "FLAGGED";
	public static final String PENDING = "PENDING";
	public static final String FAILED = "FAILED";
	public static final String CANCELLED = "CANCELLED";
}
