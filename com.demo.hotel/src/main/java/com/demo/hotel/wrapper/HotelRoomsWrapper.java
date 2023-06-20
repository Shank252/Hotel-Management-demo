package com.demo.hotel.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HotelRoomsWrapper {

	private Integer roomId;
	private String roomNo;
	private String roomType;
	private String status;
	
	public HotelRoomsWrapper(Integer roomId, String roomNo, String roomType, String status) {
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.status = status;
		}
}
