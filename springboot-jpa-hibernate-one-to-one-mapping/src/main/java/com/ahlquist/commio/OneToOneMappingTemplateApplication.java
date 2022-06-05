package com.ahlquist.commio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ahlquist.commio.model.Employee;
import com.ahlquist.commio.model.EmployeeContact;
import com.ahlquist.commio.repository.EmployeeRepository;

@SpringBootApplication
public class OneToOneMappingTemplateApplication implements CommandLineRunner {

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(OneToOneMappingTemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee employee = new Employee();
		employee.setName("Douglas");
		employee.setSalary(12500);
		EmployeeContact contact = new EmployeeContact();
		contact.setPhoneNo(4086690268l);
		employee.setEmployeeContact(contact);
		employeeRepository.save(employee);

	}

}
