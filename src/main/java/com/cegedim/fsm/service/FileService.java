package com.cegedim.fsm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cegedim.fsm.exceptions.FileNotFoundException;
import com.cegedim.fsm.model.FileModel;
import com.cegedim.fsm.model.User;
import com.cegedim.fsm.repository.FileRepository;
import com.cegedim.fsm.repository.UserRepository;

@Service
public class FileService {
	@Autowired
	private FileRepository filesRepo;
	@Autowired
	private UserRepository userRepo;
	
	public FileModel saveOrUpdate(FileModel file, String principalName) {
		//Check if file exists in DB and belongs to USER (principal Name)
		if(file.getId()!=null) {
			FileModel existingFile= filesRepo.getById(file.getId());
			if(existingFile==null) {
				throw new FileNotFoundException("No file with id '" + file.getId() + "' exists in Database");
			}
			//Use service getFIle method to try to get file
			//if errors, will be throw at that method
			getFile(file.getId(), principalName);
		}
		if(file.getFilename()!=null) {
			FileModel existingFile= filesRepo.findAllByFilename(file.getFilename());
			
			if(existingFile!=null && existingFile.getUserOwner().equals(principalName)) {
				throw new FileNotFoundException("A file with the same name exists in your repository, Please select another one!");
			}
		}
		
		//Set user files relationship
		User user= userRepo.findByUsername(principalName);
		file.setUser(user);
		file.setUserOwner(principalName);
		//Set serial
		user.setFileSerial(user.getFileSerial()+1);
		file.setSerial(String.valueOf(user.getFileSerial()));

		return filesRepo.save(file);
	}
	
	public Iterable<FileModel> getAllFiles(String principalName) {
		//File all files belonging to the logged in user
		return filesRepo.findAllByUserOwner(principalName);
	}
	
	public FileModel getFile(Long id, String principalName) {
		//Attempt to get file by id
		FileModel file= filesRepo.getById(id);
		if(file==null) {
			throw new FileNotFoundException("No file with serial: '" + id + "' exists in Database");
		}
		//check if it belongs to user
		if(!file.getUserOwner().equals(principalName)) {
			throw new FileNotFoundException("This file does not belong to this user");
		}
		return file;
	}
	
	public void deleteFile(FileModel file, String principalName) {
		//WIll be sent correct file in controller layer
		User user= userRepo.findByUsername(principalName);
		user.setFileSerial(user.getFileSerial()+1);
		filesRepo.delete(file);
	}
}
