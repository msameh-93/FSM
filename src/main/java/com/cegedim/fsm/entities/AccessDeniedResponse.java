package com.cegedim.fsm.entities;

public class AccessDeniedResponse {
	private String accessDenied;

	public AccessDeniedResponse() {
		super();
		this.accessDenied= "This action is not available to your role!";
	}
	public String getAccessDenied() {
		return accessDenied;
	}

	public void setAccessDenied(String accessDenied) {
		this.accessDenied = accessDenied;
	}
	
	
}
