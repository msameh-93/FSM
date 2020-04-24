package com.cegedim.fsm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cegedim.fsm.exceptions.FileNotFoundException;
import com.cegedim.fsm.model.File;
import com.cegedim.fsm.repository.FileRepository;

@Service
public class FileService {
	@Autowired
	private FileRepository filesRepo;
	
	public File saveOrUpdate(File file) {
		
		file.setId(file.getId());		//Id provided during udpate ops
		
		return filesRepo.save(file);
	}
	public Iterable<File> getAllFiles() {
		
		return filesRepo.findAll();
	}
	public File getFile(Long id) {
		File file= filesRepo.getById(id);
		if(file==null) {
			throw new FileNotFoundException("No file with serial: '" + id + "' exists in Database");
		}
		return file;
	}
	public void deleteFile(File file) {
		filesRepo.delete(file);
	}
}
