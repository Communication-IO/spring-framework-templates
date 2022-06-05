package com.ahlquist.commio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class JpaH2DbTemplateApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(JpaH2DbTemplateApplication.class);

	@Autowired
	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(JpaH2DbTemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("StartApplication...");

		repository.save(new User("Douglas"));
		repository.save(new User("Rein"));

		/*System.out.println("\nfindAll()");
		repository.findAll().forEach(x -> System.out.println(x));

		System.out.println("\nfindById(1L)");
		repository.findById(1l).ifPresent(x -> System.out.println(x));

		System.out.println("\nfindByName('Node')");
		repository.findByName("Douglas").forEach(x -> System.out.println(x));*/

	}

}
