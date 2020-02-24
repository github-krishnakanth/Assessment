package com.iis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class ProductSecurityConfig extends WebSecurityConfigurerAdapter {
	
	final static String ACCESS_PATH = "/products/**";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST, ACCESS_PATH).authenticated()
		.antMatchers(HttpMethod.GET, ACCESS_PATH).authenticated()
		.antMatchers(HttpMethod.DELETE, ACCESS_PATH).authenticated()
		.antMatchers(HttpMethod.PUT, ACCESS_PATH).authenticated()
		.anyRequest().permitAll().and().httpBasic().and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
