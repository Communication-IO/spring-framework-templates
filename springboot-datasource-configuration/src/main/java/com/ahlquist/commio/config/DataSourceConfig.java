package com.ahlquist.commio.config;

import javax.activation.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
		dataSourceBuilder.url("jdbc:mysql:127.0.0.1:3306/mvn_test");
		dataSourceBuilder.username("testuser");
		dataSourceBuilder.password("password");

		return (DataSource) dataSourceBuilder.build();
	}

	public String getDriverClassName() {
		return null;
	}
}
