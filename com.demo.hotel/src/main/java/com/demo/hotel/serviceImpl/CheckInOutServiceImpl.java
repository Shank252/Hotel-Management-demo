package com.demo.hotel.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.hotel.POJO.CheckInOutTO;
import com.demo.hotel.constants.HotelConstants;
import com.demo.hotel.dao.CheckInOutDao;
import com.demo.hotel.dao.HotelRoomsDao;
import com.demo.hotel.service.CheckInOutService;
import com.demo.hotel.utils.HotelUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CheckInOutServiceImpl implements CheckInOutService {

	@Autowired
	CheckInOutDao checkInOutDao;
	
	@Autowired
	HotelRoomsDao hotelRoomsDao;

	@Override
	public ResponseEntity<String> checkIn(List<Map<String, String>> requestList) {
		log.info("Inside signup : ", requestList);
		try {
			String rooms = "empty";
			int listCount = requestList.size();
			if (listCount <= 10) {
				for (Map<String, String> requestMap : requestList) {
					if (checkInDataValidation(requestMap)) {
						int count = checkInOutDao.bookedCountByUsername(requestMap.get("username"));
						if (listCount + count <= 10) {
							int booked = checkInOutDao.checkBookingByRoomNo(requestMap.get("roomNo"));
							if (booked==0) {
								checkInOutDao.save(getCheckInDetailsFromMap(requestMap));
								hotelRoomsDao.getBookingUpdateValues(requestMap.get("roomNo"), "Not Available");
							} else {
								if(rooms.equals("empty")) {
									rooms = requestMap.get("roomNo");
								}else {
									rooms = rooms+","+ requestMap.get("roomNo");
								}
							}
						} else {
							return HotelUtils.getResponseEntity(HotelConstants.CANNOT_BOOK_MORE_THAN_TEN_ROOMS,
									HttpStatus.BAD_REQUEST);
						}
					} else {
						return HotelUtils.getResponseEntity(HotelConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
					}
				}
			} else {
				return HotelUtils.getResponseEntity(HotelConstants.CANNOT_BOOK_MORE_THAN_TEN_ROOMS,
						HttpStatus.BAD_REQUEST);
			}
			if(rooms.equals("empty")) {
				return HotelUtils.getResponseEntity(HotelConstants.ROOMS_BOOKED, HttpStatus.CREATED);
			}else {
				return HotelUtils.getResponseEntity("Room No - "+rooms+" are already booked", HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean checkInDataValidation(Map<String, String> requestMap) {
		if (requestMap.containsKey("username") && requestMap.containsKey("roomNo")) {
			return true;
		}
		return false;
	}

	private CheckInOutTO getCheckInDetailsFromMap(Map<String, String> requestMap) {
		CheckInOutTO checkInOutTO = new CheckInOutTO();

		checkInOutTO.setUsername(requestMap.get("username"));
		checkInOutTO.setRoomNo(requestMap.get("roomNo"));
		checkInOutTO.setCheckInStatus("Booked");
		
		return checkInOutTO;

	}

	@Override
	public ResponseEntity<String> checkOut(List<Map<String, String>> requestList) {
		try {
			for (Map<String, String> requestMap : requestList) {
				if (checkInDataValidation(requestMap)) {
					checkInOutDao.updateCheckOut(requestMap.get("roomNo"),requestMap.get("username"),"CheckedOut");
					hotelRoomsDao.getBookingUpdateValues(requestMap.get("roomNo"), "Available");
				} else {
					return HotelUtils.getResponseEntity(HotelConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
				}
			}

			return HotelUtils.getResponseEntity(HotelConstants.ROOMS_CHECKED_OUT, HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
