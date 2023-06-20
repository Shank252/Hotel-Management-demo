package com.demo.hotel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.demo.hotel.POJO.UserDetailsTO;
import com.demo.hotel.wrapper.UserDetailsWrapper;

public interface UserDetailsDao extends JpaRepository<UserDetailsTO, Integer> {

	UserDetailsTO findDetailsByUsername(@Param("username") String string);
	
	List<UserDetailsWrapper> getUserDetails();
}
