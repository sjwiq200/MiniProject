package com.model2.mvc.service.domain;

public class RoomUser {

	
	private String user;
	private String status;
	
	public RoomUser() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "RoomUser [user=" + user + ", status=" + status + "]";
	}

	

}
