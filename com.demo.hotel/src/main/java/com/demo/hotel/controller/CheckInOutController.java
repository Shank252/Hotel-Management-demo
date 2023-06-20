package com.demo.hotel.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(path = "/checkInOut")
public interface CheckInOutController {

	@PostMapping(path = "/checkIn")
	public ResponseEntity<String> checkIn(@RequestBody(required = true) List<Map<String,String>> requestMap);
		
	@PostMapping(path = "/checkOut")
	public ResponseEntity<String> checkOut(@RequestBody(required = true) List<Map<String,String>> requestMap);
	
	
	
}
