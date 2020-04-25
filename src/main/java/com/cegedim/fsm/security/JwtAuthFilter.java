package com.cegedim.fsm.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cegedim.fsm.model.User;
import com.cegedim.fsm.service.UserDetailService;


public class JwtAuthFilter extends OncePerRequestFilter{
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	//Called on each request, filter
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			//Get Bearer Token from client header request
			String bearerToken= request.getHeader(SecurityConstants.HEADER_STRING);
			//Check if header is not empty and it starts wit "Bearer"
			if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
				//Extract JWT
				String jwt= bearerToken.substring(7, bearerToken.length());
				//Ensures JWT is not empty and validate token 
				if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
					//extract user id from decoding token
					Long userId= jwtTokenProvider.getUserIdFromToken(jwt);
					//get user that belongs to this token from DB
					User user= userDetailService.loadUserById(userId);
					//Authenticator with username
					UsernamePasswordAuthenticationToken authToken= 
							new UsernamePasswordAuthenticationToken(user.getUsername(), null, Collections.emptyList());
					//Set Authenticator details
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					//Assign Authenticator to context
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		} catch(Exception e) {
			System.out.println("ERROR IN FILTER");
		}
		//Call next in filter chain
		filterChain.doFilter(request, response);
	}
	
}
