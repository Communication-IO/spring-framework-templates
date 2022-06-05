package com.ahlquist.commio.record;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ahlquist.commio.record.entity.User;
import com.ahlquist.commio.record.repsitory.UserRepository;

@SpringBootApplication
public class JavaRecordTemplateApplication 
        implements CommandLineRunner {

	@Autowired
	UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.
		   run(JavaRecordTemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
        //Inserting dummy data
		User user1 = 
				new User("user", 
						 "lastname", "dummy@gmail.com");
		User user2 = 
				new User("user 2", 
						 "lastname 2", "hooverdude@gmail.com");
		List<User> users = new ArrayList<User>();
		users.add(user2);
		users.add(user1);
		repository.saveAll(users);

	}
}
