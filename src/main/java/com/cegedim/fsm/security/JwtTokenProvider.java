package com.cegedim.fsm.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.cegedim.fsm.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	public String generateToken(Authentication auth) {
		//Create claims 
		User user=(User) auth.getPrincipal();
		String userId= Long.toString(user.getId());
		Date now= new Date(System.currentTimeMillis());
		Date expiryTime= new Date(now.getTime()+SecurityConstants.TOKEN_EXPIRY);
		//Encode JWT
		Map<String, Object> claims= new HashMap<>();
		claims.put("id", userId);
		claims.put("username", user.getUsername());
		claims.put("fullname", user.getFullname());
		//Build Token
		return Jwts.builder()
				.setSubject(userId)
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiryTime)
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
				.compact();
	}
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public Long getUserIdFromToken(String token) {
		Claims claims= Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
		return Long.parseLong((String)claims.get("id"));
	}
}
