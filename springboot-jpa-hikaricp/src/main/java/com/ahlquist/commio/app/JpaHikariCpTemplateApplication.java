package com.ahlquist.commio.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ahlquist.commio.entity.User;
import com.ahlquist.commio.repository.UserRepository;

@EnableJpaRepositories(basePackages = "com.ahlquist.commio.repository")
@SpringBootApplication
@ComponentScan({ "com" })
@EntityScan("com.ahlquist.commio.entity")
public class JpaHikariCpTemplateApplication implements CommandLineRunner {

	@Autowired
	private UserRepository repository;
	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(JpaHikariCpTemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("DATASOURCE = " + dataSource.getClass());
		repository.save(new User("Douglas"));
		repository.save(new User("Rein"));

	}

}
