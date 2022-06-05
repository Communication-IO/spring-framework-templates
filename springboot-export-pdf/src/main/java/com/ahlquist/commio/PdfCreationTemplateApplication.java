package com.ahlquist.commio;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ahlquist.commio.model.Employee;
import com.ahlquist.commio.repository.EmployeeRepository;

@SpringBootApplication
public class PdfCreationTemplateApplication implements CommandLineRunner {

	@Autowired
	EmployeeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(PdfCreationTemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (repository.count() == 0) {
			// save a list of Employees
			repository.saveAll(Arrays.asList(
					new Employee("FirstName1", "lastName1"), 
					new Employee("FirstName2", "lastName2"),
					new Employee("FirstName3", "lastName3"), 
					new Employee("FirstName4", "lastName4"),
					new Employee("FirstName5", "lastName5")));
		}

	}
}
