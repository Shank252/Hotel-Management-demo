package com.demo.hotel.controllerImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hotel.constants.HotelConstants;
import com.demo.hotel.controller.CheckInOutController;
import com.demo.hotel.service.CheckInOutService;
import com.demo.hotel.utils.HotelUtils;


@RestController
public class CheckInOutControllerImpl implements CheckInOutController {

	@Autowired
	CheckInOutService checkInOutService;

	@Override
	public ResponseEntity<String> checkIn(List<Map<String, String>> requestMap) {
		try {
			return checkInOutService.checkIn(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	public ResponseEntity<String> checkOut(List<Map<String, String>> requestMap) {
		try {
			return checkInOutService.checkOut(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
}
