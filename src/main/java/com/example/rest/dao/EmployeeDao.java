package com.example.rest.dao;

import java.util.HashMap;
import java.util.Map;

import com.example.rest.model.Employee;


public enum EmployeeDao {
	instance;
	
	private Map<String, Employee> employeeData = new HashMap<String, Employee>();
	
	private EmployeeDao(){
		
		Employee employee = new Employee("IIIPL-2802","Himanshu","CxR");
		employeeData.put(employee.getId(),employee);	
		employee = new Employee("IIIPL-2795","Saheed","iLabs");
		employeeData.put(employee.getId(), employee);
	}
	public Map<String, Employee> getEmployeeData(){
		return employeeData;
	}
	
}