package com.demo.hotel.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.hotel.POJO.HotelRoomsTO;
import com.demo.hotel.constants.HotelConstants;
import com.demo.hotel.dao.HotelRoomsDao;
import com.demo.hotel.service.HotelRoomsService;
import com.demo.hotel.utils.HotelUtils;
import com.demo.hotel.wrapper.HotelRoomsWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotelRoomsServiceImpl implements HotelRoomsService {

	@Autowired
	HotelRoomsDao hotelRoomsDao;

	@Override
	public ResponseEntity<String> roomConfig(Map<String, String> requestMap) {
		log.info("Inside Rooms Configuration : ", requestMap);
		try {
			if (hotelRoomsDataValidation(requestMap)) {
				HotelRoomsTO hotelRoomsTO = hotelRoomsDao.findDetailsByRoomNo(requestMap.get("roomNo"));
				if (Objects.isNull(hotelRoomsTO)) {
					hotelRoomsDao.save(getHotelRoomDetailsFromMap(requestMap));
					return HotelUtils.getResponseEntity(HotelConstants.ROOM_REGISTERED_SUCCESS, HttpStatus.CREATED);
				} else {
					return HotelUtils.getResponseEntity(HotelConstants.ROOM_ALREADY_EXISTS,HttpStatus.BAD_REQUEST);
				}
			} else {
				return HotelUtils.getResponseEntity(HotelConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean hotelRoomsDataValidation(Map<String, String> requestMap) {
		if (requestMap.containsKey("roomNo") && requestMap.containsKey("roomType")
				&& requestMap.containsKey("status")) {
			return true;
		}
		return false;
	}

	private HotelRoomsTO getHotelRoomDetailsFromMap(Map<String, String> requestMap) {
		HotelRoomsTO hotelRoomsTO = new HotelRoomsTO();

		hotelRoomsTO.setRoomNo(requestMap.get("roomNo"));
		hotelRoomsTO.setRoomType(requestMap.get("roomType"));
		hotelRoomsTO.setStatus(requestMap.get("status"));

		return hotelRoomsTO;

	}

	@Override
	public ResponseEntity<List<HotelRoomsWrapper>> getAllRooms() {
		try {
			return new ResponseEntity<>(hotelRoomsDao.getAllRooms(),HttpStatus.OK);		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<HotelRoomsWrapper>> listAvailableRooms() {
		try {
			return new ResponseEntity<>(hotelRoomsDao.listAvailableRooms("Available"),HttpStatus.OK);		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
