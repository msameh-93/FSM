package com.cegedim.fsm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cegedim.fsm.model.FileModel;

//Basic JPA crud operations
@Repository
public interface FileRepository extends CrudRepository<FileModel, Long>{
	FileModel getById(Long id);
	Iterable<FileModel> findAllByUserOwner(String fileOwner);
}
