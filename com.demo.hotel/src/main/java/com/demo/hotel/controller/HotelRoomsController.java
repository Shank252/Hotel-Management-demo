package com.demo.hotel.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.hotel.wrapper.HotelRoomsWrapper;

@RequestMapping(path = "/hotelRooms")
public interface HotelRoomsController {

	@PostMapping(path = "/roomConfig")
	public ResponseEntity<String> roomConfig(@RequestBody(required = true) Map<String,String> requestMap);
	
	@GetMapping(path = "/getAllRooms")
	public ResponseEntity<List<HotelRoomsWrapper>> getAllRooms();

	@GetMapping(path = "/listAvailableRooms")
	public ResponseEntity<List<HotelRoomsWrapper>> listAvailableRooms();
	
	
	
}
