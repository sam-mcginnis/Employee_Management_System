package com.cognixia.advancedjava.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.advancedjava.ems.connection.ConnectionManager;
import com.cognixia.advancedjava.ems.models.Department;
import com.cognixia.advancedjava.ems.models.Employee;

public class DepartmentDAOClass implements DepartmentDAO {
	
	Connection conn = ConnectionManager.getConnection();
//	PreparedStatement depStmt = conn.prepareStatement("Select * FROM department");
//	PreparedStatement empStmt = conn.prepareStatement("SELECT * FROM employee WHERE departmentID IN (SELECT departmentID FROM department WHERE departmentName= ?);");
//	ResultSet depRS;
//	ResultSet empRS;
	@Override
	public List<Department> getAllDepartments() {
		List<Department> depts = new ArrayList<>();
		try {
			Statement stmt1 = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			ResultSet deptRS = stmt1.executeQuery("Select * FROM department");
			
			while (deptRS.next()) {
				List<Employee> empRoster = new ArrayList<>();
				int depID = deptRS.getInt("departmentID");
				String depName = deptRS.getString("departmentName");
				String depNum = deptRS.getString("departmentPhoneNum");
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employee WHERE departmentID IN (SELECT departmentID FROM department WHERE departmentID= ?);");
				pstmt.setInt(1,depID);
				ResultSet empRS= pstmt.executeQuery();
				
				while(empRS.next()) {
					Employee employee = new Employee(empRS.getString("employeeName"), empRS.getInt("departmentID"), empRS.getInt("employeeID"), empRS.getInt("employeeAge"));
					empRoster.add(employee);
				}
	
				Department d = new Department(depID, depName, depNum, empRoster);
				depts.add(d);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return depts;
	}

	@Override
	public Department getDepartment(int id) {
		Department dept = null;
		try {
			PreparedStatement pstmt1 = conn.prepareStatement("Select * FROM department WHERE departmentID = ?");
			pstmt1.setInt(1, id);
			ResultSet deptRS = pstmt1.executeQuery();
			while (deptRS.next()) {
			List<Employee> empRoster = new ArrayList<>();
			
			int depID = deptRS.getInt("departmentID");
			String depName = deptRS.getString("departmentName");
			String depNum = deptRS.getString("departmentPhoneNum");
			
			PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM employee WHERE departmentID IN (SELECT departmentID FROM department WHERE departmentID= ?);");
			pstmt2.setInt(1,depID);
			ResultSet empRS= pstmt2.executeQuery();
			
			while(empRS.next()) {
				Employee employee = new Employee(empRS.getString("employeeName"), empRS.getInt("departmentID"), empRS.getInt("employeeID"), empRS.getInt("employeeAge"));
				empRoster.add(employee);
			}
			
			
				Department d = new Department(deptRS.getInt("departmentID"), deptRS.getString("departmentName"), deptRS.getString("departmentPhoneNum"), empRoster);
				dept = d;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}

	@Override
	public boolean addDepartment(Department department) {
		int rows = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO department (departmentName, departmentPhoneNum)"
					+ " VALUES (?,?)");
			pstmt.setString(1, department.getName());
			pstmt.setString(2, department.getPhone());
			rows = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows == 1;
	}

	@Override
	public boolean deleteDepartment(int id) {
		int rows= 0;
		// TODO Auto-generated method stub
		try { 
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM department WHERE departmentID = ?");
			pstmt.setInt(1, id);
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows ==1;
	}

	@Override
	public boolean updateDepartment(Department department) {
		int rows= 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE department SET departmentName= ?, departmentPhoneNum = ? WHERE departmentID = ?");
			pstmt.setString(1, department.getName());
			pstmt.setString(2, department.getPhone());
			pstmt.setInt(3, department.getId());
			rows = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return rows ==1;
	}
}
