package com.cegedim.fsm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cegedim.fsm.exceptions.ErrorMapper;
import com.cegedim.fsm.exceptions.FileNotFoundException;
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
	public ResponseEntity<?> saveFile(
			@RequestPart("filename") String filename,
			@RequestPart("description") String description,
			@RequestPart("file")MultipartFile file,
			Principal principal) {
		//principal==owner of token - Assigned in oncePerRequest filter (Spring security)
		/*
		 * Validate 
		 */
		FileModel myFile= null;		
		try {
			myFile= new FileModel(filename, description, file.getBytes());
		} catch (Exception e){
			throw new FileNotFoundException("File Format is not supported");
		}
		
		return new ResponseEntity<FileModel>(filesServ.saveOrUpdate(myFile, principal.getName()), HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/download/{serial}", produces= "text/plain")
	public HttpEntity<?> download(@PathVariable("serial") Long id, Principal principal) {
		FileModel fileEntity= filesServ.getFile(id, principal.getName());
		System.out.println(">>>>>>>>>>>DOWNLOAD REQUESTED");
	    
		byte[] documentBody= fileEntity.getFile();
		
	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.TEXT_PLAIN);
	    header.set(HttpHeaders.CONTENT_DISPOSITION,
	                   "attachment; filename=" + fileEntity.getFilename().replace(" ", "_"));
	    header.setContentLength(documentBody.length);

	    return new HttpEntity<byte[]>(documentBody, header);
	}
	@GetMapping
	public ResponseEntity<?> getAllFiles(Principal principal) {
		//get all files assigned to logged in user (principal assigned in filter)
		return new ResponseEntity<Iterable<FileModel>>(filesServ.getAllFiles(principal.getName()), HttpStatus.OK);
	}
	
	@GetMapping("/{serial}")
	public ResponseEntity<?> getFile(@PathVariable("serial") Long id, Principal principal) {
		//find file through id and ensures it belongs to user
		return new ResponseEntity<FileModel>(filesServ.getFile(id, principal.getName()), HttpStatus.OK);
	}
	
	@DeleteMapping("/{serial}")
	public ResponseEntity<?> deleteFile(@PathVariable("serial") Long id, Principal principal) {
		filesServ.deleteFile(filesServ.getFile(id, principal.getName()));
		return new ResponseEntity<String>("File with serial: '" + id + "' succesfully deleted", HttpStatus.OK);
	}
}
