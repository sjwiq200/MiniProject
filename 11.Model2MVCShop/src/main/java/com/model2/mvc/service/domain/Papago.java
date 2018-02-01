package com.model2.mvc.service.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.json.simple.JSONObject;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Papago {
	
	private Message message;

	public Papago() {
		// TODO Auto-generated constructor stub
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}	

}






