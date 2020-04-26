package com.cegedim.fsm.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

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

import com.cegedim.fsm.model.FileModel;
import com.cegedim.fsm.service.FileService;

@RestController
@RequestMapping("/api/files")
public class FileController {
	@Autowired
	private FileService filesServ;	//file service layer
	private Map<String, String> errorMap= new HashMap<>();

	@PostMapping
	public ResponseEntity<?> saveFile(
			@RequestPart(name= "filename", required= false) String filename,
			@RequestPart(name= "description", required= false) String description,
			@RequestPart(name= "file", required= false)MultipartFile file,
			Principal principal) throws Exception{
		//principal==owner of token - Assigned in oncePerRequest filter (Spring security)
		//Validation check
		errorMap.clear();
		if(filename==null) {
			errorMap.put("filename", "Please Provide a valid file to upload");
		}
		if(description==null) {
			errorMap.put("description", "Please Provide a valid description for file");
		}
		if(!errorMap.isEmpty()) {
			return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
		}
		FileModel myFile= new FileModel(filename, description);	
		//Save to one folder path
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		myFile.setPath(absolutePath + "/src/main/resources/uploads/");
		byte[] bytes = file.getBytes();
		Path path = Paths.get(myFile.getPath() + file.getOriginalFilename());
		Files.write(path, bytes);
		/****************************/
		return new ResponseEntity<FileModel>(filesServ.saveOrUpdate(myFile, principal.getName()), HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/download/{serial}", produces= "text/plain")
	public HttpEntity<?> download(@PathVariable("serial") Long id, Principal principal) throws Exception {
		FileModel fileEntity= filesServ.getFile(id, principal.getName());

		//Read from directory path
		Path currentPath= Paths.get(fileEntity.getPath() + fileEntity.getFilename());
		byte[] documentBody= Files.readAllBytes(currentPath );
		//Set headers for download
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
		filesServ.deleteFile(filesServ.getFile(id, principal.getName()), principal.getName());
		return new ResponseEntity<String>("File with serial: '" + id + "' succesfully deleted", HttpStatus.OK);
	}
}
