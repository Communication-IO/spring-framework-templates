package com.ahlquist.commio.repository;

import org.springframework.data.repository.CrudRepository;

import com.ahlquist.commio.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}