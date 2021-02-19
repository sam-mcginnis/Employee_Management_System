package com.cognixia.advancedjava.ems.dao;

import java.util.List;

import com.cognixia.advancedjava.ems.models.Department;
import com.cognixia.advancedjava.ems.models.Employee;

public class DAORunner {
	public static void main(String[] args) {
		
		DepartmentDAO dc = new DepartmentDAOClass();
		
		List<Department> depts = dc.getAllDepartments();
		
		System.out.println(depts);
		
		EmployeeDAO sd = new EmployeeDAOClass();
		
		
		System.out.println(sd.getEmployees());
	}
}
