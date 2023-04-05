package com.neosoft.service;

import java.util.List;

import com.neosoft.model.Employee;

public interface EmployeeService {
	
	void insertEmployee(Employee emp1);
	void insertEmployees(List<Employee> emp2);
	
	List<Employee> listAllEmployee();
	
	Employee getEmployeeById(String empId);

}

