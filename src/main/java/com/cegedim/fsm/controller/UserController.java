package com.cegedim.fsm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cegedim.fsm.exceptions.ErrorMapper;
import com.cegedim.fsm.model.JwtResponse;
import com.cegedim.fsm.model.LoginRequest;
import com.cegedim.fsm.model.User;
import com.cegedim.fsm.security.JwtTokenProvider;
import com.cegedim.fsm.security.SecurityConstants;
import com.cegedim.fsm.service.UserService;
import com.cegedim.fsm.service.UserValidator;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userServ;				//Service layer
	@Autowired
	private ErrorMapper errorMap;				//Maps errors to fields to be consumed by react
	@Autowired
	private UserValidator userValidator;		//validates sign up user (password limits)
	@Autowired
	private AuthenticationManager authManager;	//Authentication authenticate user attempting to log in
	@Autowired
	private JwtTokenProvider jwtTokenProvider;	//used to assign a jwt to user if succesfully logged in
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody@Valid User user, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);
		if(bindingResult.hasErrors())
		{
			return errorMap.validate(bindingResult);
		}
		return new ResponseEntity<User>(userServ.save(user), HttpStatus.OK);
	}
	@PostMapping("/signin")
	public ResponseEntity<?>  signIn(@RequestBody@Valid LoginRequest login, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return errorMap.validate(bindingResult);
		}
		Authentication auth= authManager.authenticate(
				//Authenticate a usernameandpassword token (using UserDetail service against DB)
				new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		//Assign jwt Bearer...
		String jwt= SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(auth);
		//Send jwt back to client
		return new ResponseEntity<JwtResponse>(new JwtResponse(true, jwt), HttpStatus.OK);
	}
}
