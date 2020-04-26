package com.cegedim.fsm.entities;

//DTO for invalid log in credentials
public class NotAuthResponse {
	private String notAuthAccess;
	
	public NotAuthResponse() {
		super();
		this.notAuthAccess= "Access denied and/or bearer token missing";
	}

	public String getNotAuthAccess() {
		return notAuthAccess;
	}

	public void setNotAuthAccess(String notAuthAccess) {
		this.notAuthAccess = notAuthAccess;
	}

	
}
