package com.cegedim.fsm.model;

//DTO for invalid log in credentials
public class InvalidLoginResponse {
	private String username;
	private String password;
	
	public InvalidLoginResponse() {
		super();
		this.username= "Invalid username and/or bearer token missing";
		this.password= "Invalid password";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
}
