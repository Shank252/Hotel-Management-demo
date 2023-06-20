package com.demo.hotel.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.demo.hotel.POJO.HotelRoomsTO;
import com.demo.hotel.wrapper.HotelRoomsWrapper;

public interface HotelRoomsDao extends JpaRepository<HotelRoomsTO, Integer> {

	HotelRoomsTO findDetailsByRoomNo(@Param("roomNo") String string);
	
	List<HotelRoomsWrapper> getAllRooms();

	List<HotelRoomsWrapper> listAvailableRooms(@Param("status") String string);

	@Transactional
	@Modifying
	void getBookingUpdateValues(@Param("roomNo") String string, @Param("status") String string1);
}
