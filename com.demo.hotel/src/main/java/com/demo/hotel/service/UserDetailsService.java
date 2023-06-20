package com.demo.hotel.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.demo.hotel.wrapper.UserDetailsWrapper;

public interface UserDetailsService {

	public ResponseEntity<String> signUp(Map<String, String> requestMap);

	public ResponseEntity<String> login(Map<String, String> requestMap);
	
	public ResponseEntity<List<UserDetailsWrapper>> getUserDetails();
}
