package com.cognixia.advancedjava.ems.models;

import java.util.List;

public class Department {

	private int id;
	private String name; 
	private String phone;
	private List<Employee> employeeRoster;
	public Department(int id, String name, String phone, List<Employee> employeeRoster) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.employeeRoster = employeeRoster;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Employee> getEmployeeRoster() {
		return employeeRoster;
	}
	public void setEmployeeRoster(List<Employee> employeeRoster) {
		this.employeeRoster = employeeRoster;
	}
	@Override
	public String toString() {
		return "\nDepartment id=" + id + ", name=" + name + ", phone=" + phone + ", employeeRoster=" + employeeRoster;
	}

	
	
}
