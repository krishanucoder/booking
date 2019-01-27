package com.stays.bookingserver.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class CommonConverter {

	@Autowired
	protected ModelMapper modelMapper;

	@Autowired
	protected RestTemplate restTemplate;

}
