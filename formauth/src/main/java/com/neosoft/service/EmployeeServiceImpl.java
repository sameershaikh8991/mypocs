package com.neosoft.service;

 import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.dao.EmployeeDao;
import com.neosoft.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	
	
	@Override
	public void insertEmployee(Employee emp1) {
		// TODO Auto-generated method stub
		employeeDao.insertEmployee(emp1);
	}

	@Override
	public void insertEmployees(List<Employee> emp2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employee> listAllEmployee() {
		// TODO Auto-generated method stub
		
		return employeeDao.listAllEmployee();
	}

	@Override
	public Employee getEmployeeById(String empId) {
		// TODO Auto-generated method stub
		return null;
	}

}

