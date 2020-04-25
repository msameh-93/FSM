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
		file.setId(file.getId());		//Id provided during udpate ops
		
		//Set user files relationship
		User user= userRepo.findByUsername(principalName);
		file.setUser(user);
		file.setUserOwner(principalName);
		
		return filesRepo.save(file);
	}
	public Iterable<FileModel> getAllFiles(String principalName) {
		
		return filesRepo.findAllByUserOwner(principalName);
	}
	public FileModel getFile(Long id, String principalName) {
		FileModel file= filesRepo.getById(id);
		if(file==null) {
			throw new FileNotFoundException("No file with serial: '" + id + "' exists in Database");
		}
		if(!file.getUserOwner().equals(principalName)) {
			throw new FileNotFoundException("This file does not belong to this user");
		}
		return file;
	}
	public void deleteFile(FileModel file) {
		//WIll be sent correct file in controller layer
		filesRepo.delete(file);
	}
}
