package com.cegedim.fsm.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank(message= "Please provide login Email")
	@Email(message= "Please ENter a valid email address")
	private String username;
	@NotBlank(message= "Please provide a password")
	private String password;
	
	
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
