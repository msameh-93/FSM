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
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String bearerToken= request.getHeader(SecurityConstants.HEADER_STRING);
			if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
				String jwt= bearerToken.substring(7, bearerToken.length());
				if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
					Long userId= jwtTokenProvider.getUserIdFromToken(jwt);
					User user= userDetailService.loadUserById(userId);
					//Authenticator
					UsernamePasswordAuthenticationToken authToken= 
							new UsernamePasswordAuthenticationToken(user.getUsername(), null, Collections.emptyList());
					//Set Authenticator details
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					//Assign Authenticator to context
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		filterChain.doFilter(request, response);
	}
	
}
