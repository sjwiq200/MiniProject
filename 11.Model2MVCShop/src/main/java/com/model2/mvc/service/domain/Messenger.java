package com.model2.mvc.service.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class Messenger {


	private String _id;
	private String roomname;
	private String key;
	private String online;
	private List<RoomUser> roomUser;

	public Messenger() {
		// TODO Auto-generated constructor stub
	}
	
	

	public String getId() {
		return _id;
	}


	public void setId(String _id) {
		this._id = _id;
	}


	public String getRoomname() {
		return roomname;
	}


	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getOnline() {
		return online;
	}


	public void setOnline(String online) {
		this.online = online;
	}
	
	

	public List<RoomUser> getRoomUser() {
		return roomUser;
	}



	public void setRoomUser(List<RoomUser> roomUser) {
		this.roomUser = roomUser;
	}



	@Override
	public String toString() {
		return "Messenger [_id=" + _id + ", roomname=" + roomname + ", key=" + key + ", online=" + online
				+ ", roomUser=" + roomUser + "]";
	}



	




}
