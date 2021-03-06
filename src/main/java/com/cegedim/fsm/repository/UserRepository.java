package com.cegedim.fsm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cegedim.fsm.entities.User;

//Basic JPA crud operations
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
	User getById(Long id);
}
