package com.cegedim.fsm.model;

public class JwtResponse {
	private boolean valid;
	private String token;
	
	public JwtResponse(boolean valid, String token) {
		super();
		this.valid = valid;
		this.token = token;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
