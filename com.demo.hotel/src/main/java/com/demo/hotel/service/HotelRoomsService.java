package com.demo.hotel.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.demo.hotel.wrapper.HotelRoomsWrapper;

public interface HotelRoomsService {

	public ResponseEntity<String> roomConfig(Map<String, String> requestMap);
	
	public ResponseEntity<List<HotelRoomsWrapper>> getAllRooms();
	
	public ResponseEntity<List<HotelRoomsWrapper>> listAvailableRooms();

}
