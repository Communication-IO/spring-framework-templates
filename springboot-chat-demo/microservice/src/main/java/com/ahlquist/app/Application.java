package com.ahlquist.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ahlquist.aws.AWSS3Service;

@EnableJpaRepositories(
		basePackages = { "com.ahlquist.models", "com.ahlquist.repositories" })
@ComponentScan({ 
	"com.ahlquist.controllers", "com.ahlquist.services", 
	"com.ahlquist.models", "com.ahlquist.repositories" })
@EntityScan("com.ahlquist.models")
@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static AWSS3Service s3Service;

	public static void main(String[] args) {

		s3Service = new AWSS3Service();

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		int i = 0;
		for (String arg : args) {
			logger.debug("args[" + i++ + "] found in main(...): " + arg);
		}

		SpringApplication.run(Application.class, args);
		ctx.close();
	}
}
