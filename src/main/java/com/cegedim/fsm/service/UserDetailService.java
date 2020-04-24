package com.cegedim.fsm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cegedim.fsm.exceptions.UserNotFoundException;
import com.cegedim.fsm.model.User;
import com.cegedim.fsm.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService{
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user= userRepo.findByUsername(username);
		if(user==null) {
			throw new UserNotFoundException("No user with user name: '" + username + "' exists");
		}
		return user;
	}
	
	@Transactional
	public User loadUserById(Long id) {
		User user= userRepo.getById(id);
		if(user==null) {
			throw new UserNotFoundException("No user with id: '" + id + "' exists");
		}
		return user;
	}

}
