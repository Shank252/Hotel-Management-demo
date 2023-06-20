package com.demo.hotel.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.hotel.wrapper.UserDetailsWrapper;


@RequestMapping(path = "/userDetails")
public interface UserDetailsController {

	@PostMapping(path = "/signUp")
	public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String,String> requestMap);
	

	@PostMapping(path = "/login")
	public ResponseEntity<String> login(@RequestBody(required = true) Map<String,String> requestMap);
	
	@GetMapping(path = "/getAllUser")
	public ResponseEntity<List<UserDetailsWrapper>> getUserDetails();
	
	
	
}
