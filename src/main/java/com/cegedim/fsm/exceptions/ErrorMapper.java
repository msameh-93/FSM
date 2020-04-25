package com.cegedim.fsm.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ErrorMapper {
	//Utility class to map error messages to field and send to client as a ResponseEntity
	public ResponseEntity<Map<String, String>> validate(BindingResult bindingResult) {
		Map<String, String> errorMap= new HashMap<>();
		for(FieldError err : bindingResult.getFieldErrors()) {
			errorMap.put(err.getField(), err.getDefaultMessage());
		}
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
	}
}
