package com.demo.hotel.POJO;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;


@NamedQuery(name = "CheckInOutTO.bookedCountByUsername", query = "select count(*) from CheckInOutTO c "
		+ "where c.username =: username and c.checkInStatus = 'Booked'")


@NamedQuery(name = "CheckInOutTO.checkBookingByRoomNo", query = "select count(*) from CheckInOutTO c "
		+ "where c.roomNo =: roomNo and c.checkInStatus = 'Booked'")

@NamedQuery(name = "CheckInOutTO.updateCheckOut", query = "update CheckInOutTO c set c.checkInStatus =: checkInStatus "
		+ " where c.roomNo =: roomNo and c.username =: username and c.checkInStatus = 'Booked' ")


@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "checkInOut")
public class CheckInOutTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cId")
	private Integer cId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "roomNo")
	private String roomNo;
	
	@Column(name = "checkInStatus")
	private String checkInStatus;
}
