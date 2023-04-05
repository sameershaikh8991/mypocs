package com.neosoft.dao;

import java.util.List;

import com.neosoft.model.Employee;

public interface EmployeeDao {

	void insertEmployee(Employee emp);
	
	void insertEmployee(List<Employee> emp2);
	
	List<Employee> listAllEmployee();
	Employee getEmployeeById(int empid);
	
	
	
	
}
