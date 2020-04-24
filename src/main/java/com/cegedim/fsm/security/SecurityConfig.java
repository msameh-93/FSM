package com.cegedim.fsm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cegedim.fsm.service.UserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public BCryptPasswordEncoder bCrypt() {
		return new BCryptPasswordEncoder();
	}
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	@Bean 
	public JwtAuthFilter jwtAuthFilter() {
		return new JwtAuthFilter();
	}
	@Autowired
	private UserDetailService userDetailServ;
	@Autowired
	private JwtAuthEntryPoint jwtAuthEntryPoint;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailServ).passwordEncoder(bCrypt());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.headers().frameOptions().sameOrigin()
		.and()
		.authorizeRequests()
			.antMatchers(	
	        		"/",                         
	        		"/favicon.ico",
	                "/**/*.png",
	                "/**/*.gif",
	                "/**/*.svg",
	                "/**/*.jpg",
	                "/**/*.html",
	                "/**/*.json",
	                "/**/*.map",
	                "/**/*.css",
	                "/**/*.js").permitAll()
	        .antMatchers(SecurityConstants.SIGN_UP_URLS).permitAll()	//authorize signup endpoint
	        .antMatchers(SecurityConstants.H2URL).permitAll()
	        .anyRequest().authenticated();
		
		http.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
