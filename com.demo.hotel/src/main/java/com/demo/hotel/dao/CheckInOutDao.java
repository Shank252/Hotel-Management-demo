package com.demo.hotel.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.demo.hotel.POJO.CheckInOutTO;

public interface CheckInOutDao extends JpaRepository<CheckInOutTO, Integer> {

	int bookedCountByUsername(@Param("username") String string);
	
	int checkBookingByRoomNo(@Param("roomNo") String string);

	@Transactional
	@Modifying
	void updateCheckOut(@Param("roomNo") String string, @Param("username") String string1, @Param("checkInStatus") String string2);
	
	
}
