package com.cegedim.fsm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileNotFoundException extends RuntimeException {
	//Thrown from controllers and services layers
	public FileNotFoundException(String msg) {
		super(msg);
	}
}
