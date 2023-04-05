package com.neosoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neosoft.model.Employee;
import com.neosoft.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping("/welcome")
	public ModelAndView firstPage()
	{
		return new ModelAndView("welcome");
	}
	
	@RequestMapping(value="/addNewEmployee", method =RequestMethod.POST)
	public ModelAndView processRequest(@ModelAttribute("emp") Employee emp)
	{
		employeeService.insertEmployee(emp);
		List<Employee> employees = employeeService.listAllEmployee();
		ModelAndView model=new ModelAndView("getEmployess");
		model.addObject("employee",employees);
		
		return model;
	}
	
	@RequestMapping("/getEmployees")
	public ModelAndView getEmployees()
	{
		List<Employee> employees=employeeService.listAllEmployee();
		ModelAndView model=new ModelAndView("getEmployess");
		model.addObject("employee",employees);
		
		return model;
	}
}