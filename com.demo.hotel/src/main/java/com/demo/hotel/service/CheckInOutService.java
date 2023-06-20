package com.demo.hotel.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface CheckInOutService {

	public ResponseEntity<String>  checkIn(List<Map<String, String>> requestMap);

	public ResponseEntity<String>  checkOut(List<Map<String, String>> requestMap);
}
