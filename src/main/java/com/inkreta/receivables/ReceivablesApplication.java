package com.inkreta.receivables;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.tecnics.inkreta.config.JwtAuthenticationEntryPoint;
import com.tecnics.inkreta.config.JwtRequestFilter;
import com.tecnics.inkreta.config.JwtTokenUtil;

@SpringBootApplication
public class ReceivablesApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ReceivablesApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ReceivablesApplication.class);
    }
	
	@Bean
	public JwtRequestFilter setJwtRequestFilter()
	{
		return new JwtRequestFilter();
	}
	
	@Bean
	public JwtAuthenticationEntryPoint setJwtAuthenticationEntryPoint()
	{
		return new JwtAuthenticationEntryPoint();
	}
	
	@Bean
	public JwtTokenUtil setJwtTokenUtil()
	{
		return new JwtTokenUtil();
	}

}
