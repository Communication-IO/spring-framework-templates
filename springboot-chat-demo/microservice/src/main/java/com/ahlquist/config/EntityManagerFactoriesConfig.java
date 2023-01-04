package com.ahlquist.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class EntityManagerFactoriesConfig {

	final public String[] packages = { "com.ahlquist.models", "com.ahlquist.repositories" };

	@Autowired
	private DataSource dataSource;

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean emf() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPackagesToScan(packages);
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return emf;
	}
}