package com.cegedim.fsm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<Object> handleFileNotFound(FileNotFoundException exc, WebRequest web) {
		FileNotFoundResponse response= new FileNotFoundResponse(exc.getMessage());
		
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<Object> handleFileNotFound(UserNotFoundException exc, WebRequest web) {
		UserNotFoundResponse response= new UserNotFoundResponse(exc.getMessage());
		
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
}
