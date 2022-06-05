package com.ahlquist.commio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahlquist.commio.model.Employee;
import com.ahlquist.commio.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> fetchAll() {
		return (List<Employee>) employeeRepository.findAll();

	}

}
