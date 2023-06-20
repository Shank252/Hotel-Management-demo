package com.demo.hotel.controllerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hotel.constants.HotelConstants;
import com.demo.hotel.controller.HotelRoomsController;
import com.demo.hotel.service.HotelRoomsService;
import com.demo.hotel.utils.HotelUtils;
import com.demo.hotel.wrapper.HotelRoomsWrapper;


@RestController
public class HotelRoomsControllerImpl implements HotelRoomsController {

	@Autowired
	HotelRoomsService hotelRoomsService;

	@Override
	public ResponseEntity<String> roomConfig(Map<String, String> requestMap) {
		try {
			return hotelRoomsService.roomConfig(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<List<HotelRoomsWrapper>> getAllRooms() {
		try {
			return hotelRoomsService.getAllRooms();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<List<HotelRoomsWrapper>> listAvailableRooms() {
		try {
			return hotelRoomsService.listAvailableRooms();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
