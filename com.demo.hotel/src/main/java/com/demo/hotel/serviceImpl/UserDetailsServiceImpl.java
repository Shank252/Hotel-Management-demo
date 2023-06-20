package com.demo.hotel.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.hotel.POJO.UserDetailsTO;
import com.demo.hotel.constants.HotelConstants;
import com.demo.hotel.dao.UserDetailsDao;
import com.demo.hotel.service.UserDetailsService;
import com.demo.hotel.utils.HotelUtils;
import com.demo.hotel.wrapper.UserDetailsWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailsDao userDetailsDao;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		log.info("Inside signup : ", requestMap);
		try {
			if (signUpDataValidation(requestMap)) {
				UserDetailsTO userDetailsTO = userDetailsDao.findDetailsByUsername(requestMap.get("username"));
				if (Objects.isNull(userDetailsTO)) {
					userDetailsDao.save(getUserDetailsFromMap(requestMap));
					return HotelUtils.getResponseEntity(HotelConstants.USER_REGISTERED_SUCCESS, HttpStatus.CREATED);
				} else {
					return HotelUtils.getResponseEntity(HotelConstants.USER_NAME_ALREADY_EXISTS,
							HttpStatus.BAD_REQUEST);
				}
			} else {
				return HotelUtils.getResponseEntity(HotelConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean signUpDataValidation(Map<String, String> requestMap) {
		if (requestMap.containsKey("username") && requestMap.containsKey("password")
				&& requestMap.containsKey("contactNo") && requestMap.containsKey("address")) {
			return true;
		}
		return false;
	}

	private UserDetailsTO getUserDetailsFromMap(Map<String, String> requestMap) {
		UserDetailsTO userDetailsTO = new UserDetailsTO();
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		userDetailsTO.setUsername(requestMap.get("username"));
		userDetailsTO.setPassword(bCrypt.encode(requestMap.get("password")));
		userDetailsTO.setContactNo(requestMap.get("contactNo"));
		userDetailsTO.setAddress(requestMap.get("address"));

		return userDetailsTO;

	}

	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		log.info("Inside login : ", requestMap);
		try {
			if (loginDataValidation(requestMap)) {
				UserDetailsTO userDetailsTO = userDetailsDao.findDetailsByUsername(requestMap.get("username"));
				if (Objects.isNull(userDetailsTO)) {
					return HotelUtils.getResponseEntity(HotelConstants.UNAUTHORIZED_USER, HttpStatus.NOT_FOUND);
				} else {
					BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
					if ((userDetailsTO.getUsername().equals(requestMap.get("username")))
							&& (bCrypt.matches(requestMap.get("password"),userDetailsTO.getPassword()))) {
						return HotelUtils.getResponseEntity(HotelConstants.LOGIN_SUCCESSFULL, HttpStatus.OK);
					} else {
						return HotelUtils.getResponseEntity(HotelConstants.PASSWORD_INCORRECT,
								HttpStatus.EXPECTATION_FAILED);
					}
				}
			} else {
				return HotelUtils.getResponseEntity(HotelConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean loginDataValidation(Map<String, String> requestMap) {
		if (requestMap.containsKey("username") && requestMap.containsKey("password")) {
			return true;
		}
		return false;
	}

	@Override
	public ResponseEntity<List<UserDetailsWrapper>> getUserDetails() {
		try {
			return new ResponseEntity<>(userDetailsDao.getUserDetails(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
