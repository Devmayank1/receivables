package com.inkreta.receivables.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DBConfig {

	@Value("${spring.currentDataSource.url}")
	private String dbUrl;
	
	@Value("${spring.currentDataSource.username}")
	private String dbUsername;
	
	@Value("${spring.currentDataSource.password}")
	private String dbPassword;
	
	@Value("${spring.currentDataSource.driver-class-name}")
	private String driverClass;
	
	@Bean
	public DataSource datasource() throws ClassNotFoundException
	{
		Class.forName(driverClass);
		return DataSourceBuilder.create()
				.driverClassName(driverClass)
				.url(dbUrl)
				.username(dbUsername)
				.password(dbPassword)
				.build();
	}
	
	@Bean("currentTemplate")
	@Autowired
	public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource)
	{
		return new NamedParameterJdbcTemplate(dataSource);
	}
}
