package com.cegedim.fsm.controller;

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
import com.cegedim.fsm.model.File;
import com.cegedim.fsm.service.FileService;

@RestController
@RequestMapping("/api/files")
public class FileController {
	@Autowired
	private FileService filesServ;
	@Autowired
	private ErrorMapper errorMap;
	
	@PostMapping
	public ResponseEntity<?> saveFile(@Valid@RequestBody File file, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return errorMap.validate(bindingResult);
		}
		return new ResponseEntity<File>(filesServ.saveOrUpdate(file), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllFiles() {
		return new ResponseEntity<Iterable<File>>(filesServ.getAllFiles(), HttpStatus.OK);
	}
	
	@GetMapping("/{serial}")
	public ResponseEntity<?> getFile(@PathVariable("serial") Long id) {
		return new ResponseEntity<File>(filesServ.getFile(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{serial}")
	public ResponseEntity<?> deleteFile(@PathVariable("serial") Long id) {
		filesServ.deleteFile(filesServ.getFile(id));
		return new ResponseEntity<String>("File with serial: '" + id + "' succesfully deleted", HttpStatus.OK);
	}
}
