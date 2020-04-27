package com.cegedim.fsm.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.cegedim.fsm.entities.NotAuthResponse;
import com.google.gson.Gson;

//First error Authentication level
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
	//Handles Authentication errors
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		String jsonInvalidLoginResponse= new Gson().toJson(new NotAuthResponse());
		
		response.setContentType("application/json");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		
		//response.getWriter().print(jsonInvalidLoginResponse);
		request.getRequestDispatcher("/").forward(request, response);
	}
}
