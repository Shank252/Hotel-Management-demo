package com.demo.hotel.controllerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hotel.constants.HotelConstants;
import com.demo.hotel.controller.UserDetailsController;
import com.demo.hotel.service.UserDetailsService;
import com.demo.hotel.utils.HotelUtils;
import com.demo.hotel.wrapper.UserDetailsWrapper;


@RestController
public class UserDetailsControllerImpl implements UserDetailsController {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {
			return userDetailsService.signUp(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		try {
			return userDetailsService.login(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<UserDetailsWrapper>> getUserDetails() {
		try {
			return userDetailsService.getUserDetails();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<UserDetailsWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
