package com.cognixia.advancedjava.ems.models;

public class Employee {
	private String employeeName;
	private int departmentID;
	private  int ID;
	private  int age;
	public Employee(String employeeName, int departmentID, int iD, int age) {
		super();
		this.employeeName = employeeName;
		this.departmentID = departmentID;
		ID = iD;
		this.age = age;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "\nemployeeName=" + employeeName + ", departmentID=" + departmentID + ", ID=" + ID + ", age="
				+ age;
	}
	

	
}
