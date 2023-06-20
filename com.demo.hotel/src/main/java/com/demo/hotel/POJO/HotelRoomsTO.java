package com.demo.hotel.POJO;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@NamedQuery(name = "HotelRoomsTO.findDetailsByRoomNo", query = "select r from HotelRoomsTO r where r.roomNo =: roomNo")

@NamedQuery(name = "HotelRoomsTO.getAllRooms", query = "select new com.demo.hotel.wrapper.HotelRoomsWrapper(r.roomId,r.roomNo,r.roomType,r.status)"
		+ " from HotelRoomsTO r where r.roomId > 0")

@NamedQuery(name = "HotelRoomsTO.listAvailableRooms", query = "select new com.demo.hotel.wrapper.HotelRoomsWrapper(r.roomId,r.roomNo,r.roomType,r.status)"
		+ " from HotelRoomsTO r where r.status =: status")

@NamedQuery(name = "HotelRoomsTO.getBookingUpdateValues", query = "update HotelRoomsTO r set r.status =: status"
		+ " where r.roomNo =: roomNo")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "hotelRooms")
public class HotelRoomsTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roomId")
	private Integer roomId;
	
	@Column(name = "roomNo")
	private String roomNo;
	
	@Column(name = "roomType")
	private String roomType;
	
	@Column(name = "status")
	private String status;
	
}
