package com.cognixia.advancedjava.ems.dao;

import java.util.List;

import com.cognixia.advancedjava.ems.models.Employee;
import com.cognixia.advancedjava.ems.models.MessageException;

public interface EmployeeDAO {

	List<Employee> getEmployees();
	Employee getEmployee(int id);
	boolean addEmployee(Employee Employee) throws MessageException;
	boolean updateEmployee(Employee Employee);
	boolean deleteEmployee(int id);
}
