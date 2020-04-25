package com.cegedim.fsm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends RuntimeException{
	//Thrown from controllers and service layers
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
