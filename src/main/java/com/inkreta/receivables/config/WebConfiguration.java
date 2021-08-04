package com.inkreta.receivables.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//@EnableWebSecurity
public class WebConfiguration extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure( HttpSecurity http ) throws Exception
	{
		http
			.authorizeRequests()
			.antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**","/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.csrf().disable();
	}
}
