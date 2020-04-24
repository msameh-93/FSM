package com.cegedim.fsm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cegedim.fsm.model.File;

@Repository
public interface FileRepository extends CrudRepository<File, Long>{
	File getById(Long id);
}
