package com.ahlquist.commio.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ahlquist.commio.models.User;
import com.ahlquist.commio.repositories.UserRepository;

@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "com.ahlquist.commio.repositories" })
@ComponentScan(basePackages = { "com.ahlquist.commio.repositories" })
@EntityScan("com.ahlquist.commio.models")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(UserRepository userRepository) throws Exception {
		return (String[] args) -> {
			User user1 = new User("Douglas", "douglas@communication.io");
			User user2 = new User("Rein", "rein@communication.io");
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.findAll().forEach(user -> System.out.println(user));
		};
	}
}