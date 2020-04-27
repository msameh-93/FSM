package com.cegedim.fsm.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cegedim.fsm.dto.FileDTO;
import com.cegedim.fsm.entities.FileModel;
import com.cegedim.fsm.service.FileService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/files")
@Api
public class FileController {
	@Autowired
	private FileService filesServ;	//file service layer
	private Map<String, String> errorMap= new HashMap<>();

	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<?> saveFile(
			@RequestPart(name= "id", required= false) Long id,
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
		FileModel myFile= null;
		if(id!=null) {
			myFile= filesServ.getFile(id, principal.getName());			
		} else {
			myFile= new FileModel(filename, description, file.getOriginalFilename());
		}
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
	//Rename
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/update")
	public ResponseEntity<?> updateFile(
			@RequestPart(name= "id", required= false) String myId,
			@RequestPart(name= "filename", required= false) String filename,
			Principal principal) throws Exception{
		System.out.println("HERE");
		Long id= Long.valueOf(myId);
		FileModel myFile= null;
		errorMap.clear();
		if(id!=null) {
			myFile= filesServ.getFile(id, principal.getName());			
		} else {
			errorMap.put("id", "Please Provide a valid id for file");
			return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
		}
		int dotIndex= myFile.getFilename().indexOf(".");
		String extension= myFile.getFilename().substring(dotIndex, myFile.getFilename().length());

		myFile.setId(id);
		myFile.setFilename(filename+extension);
		filesServ.saveOrUpdate(myFile, principal.getName());
		/****************************/
		return new ResponseEntity<String>("Updated successfully", HttpStatus.OK);
	}
	//Download
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/download/{serial}", produces= "text/plain")
	public HttpEntity<?> download(@PathVariable("serial") Long id, Principal principal) throws Exception {
		FileModel fileEntity= filesServ.getFile(id, principal.getName());

		//Read from directory path
		Path currentPath= Paths.get(fileEntity.getPath() + fileEntity.getOriginalFilename());
		byte[] documentBody= Files.readAllBytes(currentPath );
		//Set headers for download
	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.TEXT_PLAIN);
	    header.set(HttpHeaders.CONTENT_DISPOSITION,
	                   "attachment; filename=" + fileEntity.getFilename().replace(" ", "_"));
	    header.setContentLength(documentBody.length);

	    return new HttpEntity<byte[]>(documentBody, header);
	}
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<?> getAllFiles(Principal principal) {
		//get all files assigned to logged in user (principal assigned in filter)
		//Get files and prepare DTO
		Iterable<FileModel> files= filesServ.getAllFiles(principal.getName());
		List<FileDTO> filesDTO= new ArrayList<>();
		for(FileModel file : files) {
			filesDTO.add(FileDTO.convertoToDTO(file));
		}
		return new ResponseEntity<Iterable<FileDTO>>(filesDTO, HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/{serial}")
	public ResponseEntity<?> getFile(@PathVariable("serial") Long id, Principal principal) {
		//find file through id and ensures it belongs to user
		//Prepare DTO
		FileModel file= filesServ.getFile(id, principal.getName());
		FileDTO filesDTO= FileDTO.convertoToDTO(file);
		
		return new ResponseEntity<FileDTO>(filesDTO, HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/{serial}")
	public ResponseEntity<?> deleteFile(@PathVariable("serial") Long id, Principal principal) {
		filesServ.deleteFile(filesServ.getFile(id, principal.getName()));
		return new ResponseEntity<String>("File with serial: '" + id + "' succesfully deleted", HttpStatus.OK);
	}
}
