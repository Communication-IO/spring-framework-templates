package com.ahlquist.commio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ahlquist.commio.model.Employee;
import com.ahlquist.commio.repository.EmployeeRepository;

@SpringBootApplication
public class ExportCsvTemplateApplication implements CommandLineRunner {
	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(ExportCsvTemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Employee> employees = new ArrayList<>();

		// create dummy employees
		employees.add(new Employee("Arthur", "arthur@mycodematters.com", "England", 35, "Lead Software Architect"));
		employees.add(new Employee("Bruce", "bruce@mycodematters.com", "USA", 25, "Tester"));
		employees.add(new Employee("Charlie", "charlie@mycodematters.com", "Austria", 29, "Sr.Software Engineer"));
		employees.add(new Employee("Dean", "dean@mycodematters.com", "Thailand", 35, "Project Manager"));
		employees.add(new Employee("Eddie", "edward@mycodematters.com", "USA", 52, "CEO"));
		employees.add(new Employee("Franklin", "franklin@mycodematters.com", "Japan", 29, "Sr.Software Engineer"));
		employees.add(new Employee("George", "george@mycodematters.com", "Switzerland", 35, "Lead QA Engineer"));
		employees.add(new Employee("Hank", "hank@mycodematters.com", "USA", 25, "Tester"));
		employees.add(new Employee("Joseph", "joseph@mycodematters.com", "Japan", 29, "Sr.Software Engineer"));
		employees.add(new Employee("Larry", "larry@mycodematters.com", "Germany", 55, "CTO"));
		employees.add(new Employee("Moe", "moe@mycodematters.com", "USA", 45, "Engineering Manager"));
		employees.add(new Employee("Nathan", "nathan@mycodematters.com", "Japan", 29, "Sr.Software Engineering Director"));
		employees.add(new Employee("Oscar", "oscar@mycodematters.com", "France", 35, "Software Engineer"));
		employees.add(new Employee("Peter", "peter@mycodematters.com", "USA", 25, "Principle Software Engineer"));
		employees.add(new Employee("Quincy", "quincy@mycodematters.com", "Japan", 29, "Software Engineer"));
		employeeRepository.saveAll(employees);
		employeeRepository.saveAll(employees);
	}

}
