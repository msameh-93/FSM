package com.cegedim.fsm.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cegedim.fsm.model.User;

@Service
public class UserValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		User user=(User) target;
		
		if(user.getPassword()!=null && user.getPassword().length() < 6) {
			errors.rejectValue("password", "Length", "Password Cannot be less than 6 characters");
		}
		if(user.getPasswordConfirm()!=null && !(user.getPasswordConfirm().equals(user.getPassword()))) {
			errors.rejectValue("passwordConfirm", "Match", "Passwords Do no match");
		}
	}
}
