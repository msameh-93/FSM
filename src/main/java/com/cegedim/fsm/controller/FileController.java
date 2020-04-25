package com.cegedim.fsm.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cegedim.fsm.exceptions.ErrorMapper;
import com.cegedim.fsm.model.FileModel;
import com.cegedim.fsm.service.FileService;

@RestController
@RequestMapping("/api/files")
public class FileController {
	@Autowired
	private FileService filesServ;	//file service layer
	@Autowired
	private ErrorMapper errorMap;	//Maps errors to fields to be consumed by react-client
	
	@PostMapping
	public ResponseEntity<?> saveFile(@Valid@RequestBody FileModel file, BindingResult bindingResult, Principal principal) {
		//principal==owner of token - Assigned in oncePerRequest filter (Spring security)
		if(bindingResult.hasErrors()) {
			//Returns a response entity of errors if found
			return errorMap.validate(bindingResult);
		}
		return new ResponseEntity<FileModel>(filesServ.saveOrUpdate(file, principal.getName()), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllFiles(Principal principal) {
		//get all files assigned to logged in user (principal assigned in filter)
		return new ResponseEntity<Iterable<FileModel>>(filesServ.getAllFiles(principal.getName()), HttpStatus.OK);
	}
	
	@GetMapping("/{serial}")
	public ResponseEntity<?> getFile(@PathVariable("serial") Long id, Principal principal) {
		return new ResponseEntity<FileModel>(filesServ.getFile(id, principal.getName()), HttpStatus.OK);
	}
	
	@DeleteMapping("/{serial}")
	public ResponseEntity<?> deleteFile(@PathVariable("serial") Long id, Principal principal) {
		filesServ.deleteFile(filesServ.getFile(id, principal.getName()));
		return new ResponseEntity<String>("File with serial: '" + id + "' succesfully deleted", HttpStatus.OK);
	}
}
