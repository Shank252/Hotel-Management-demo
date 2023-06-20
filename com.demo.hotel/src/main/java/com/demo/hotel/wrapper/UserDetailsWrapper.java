package com.demo.hotel.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailsWrapper {

	private Integer uId;
	private String username;
	private String password;
	private String contactNo;
	private String address;
	
	public UserDetailsWrapper(Integer uId, String username, String password, String contactNo, String address) {
		this.uId = uId;
		this.username = username;
		this.password = password;
		this.contactNo = contactNo;
		this.address = address;
	}
}
