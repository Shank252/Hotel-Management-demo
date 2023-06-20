package com.demo.hotel.POJO;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;


@NamedQuery(name = "UserDetailsTO.findDetailsByUsername", query = "select u from UserDetailsTO u where u.username =: username")

@NamedQuery(name = "UserDetailsTO.getUserDetails", query = "select "
		+ "new com.demo.hotel.wrapper.UserDetailsWrapper(u.uId,u.username,u.password,u.contactNo,u.address) "
		+ "from UserDetailsTO u where u.uId >0")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "userDetails")
public class UserDetailsTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uId")
	private Integer uId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "contactNo")
	private String contactNo;
	
	@Column(name = "address")
	private String address;

	
	
	
	
	
	
}
