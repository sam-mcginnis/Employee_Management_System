package com.cognixia.advancedjava.ems.dao;

import java.util.List;

import com.cognixia.advancedjava.ems.models.Department;

public interface DepartmentDAO {

	List<Department> getAllDepartments();
	Department getDepartment(int id);
	boolean addDepartment(Department department);
	boolean deleteDepartment(int id);
	boolean updateDepartment(Department department);
	
}
