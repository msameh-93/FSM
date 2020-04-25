package com.cegedim.fsm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cegedim.fsm.exceptions.UserNotFoundException;
import com.cegedim.fsm.model.User;
import com.cegedim.fsm.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPass;	//Encrypt password with key before persisting to DB
	
	public User save(User user) {
		try {
			user.setPasswordConfirm("");	//remvoe password confirm
			user.setPassword(bCryptPass.encode(user.getPassword()));
			return userRepo.save(user);
		} catch(Exception e) {
			e.printStackTrace();
			throw new UserNotFoundException(user.getUsername() + " already exists!");
		}
	}
}
