package com.ahlquist.commio;

import java.util.HashSet;
import java.util.Set;

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
		employee.setSalary(200500);
		EmployeeContact contact1 = new EmployeeContact();
		contact1.setPhoneNo(4081112222L);
		EmployeeContact contact2 = new EmployeeContact();
		contact2.setPhoneNo(22222222222222l);
		Set<EmployeeContact> contacts = new HashSet<>();
		contacts.add(contact1);
		contacts.add(contact2);
		employee.setEmployeeContacts(contacts);
		employeeRepository.save(employee);

	}

}
