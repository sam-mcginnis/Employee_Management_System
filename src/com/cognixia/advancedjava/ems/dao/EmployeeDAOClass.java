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
import com.cognixia.advancedjava.ems.models.MessageException;

public class EmployeeDAOClass implements EmployeeDAO {
	
	Connection conn = ConnectionManager.getConnection();

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");
			while (rs.next()) {
				
				
				Employee employee = new Employee(rs.getString("employeeName"), rs.getInt("departmentID"), rs.getInt("employeeID"), rs.getInt("employeeAge"));

												
				employees.add(employee);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employees;
	}

	@Override
	public Employee getEmployee(int id) {
		Employee employee = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement("Select * FROM employee WHERE employeeID = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				employee = new Employee(rs.getString("employeeName"), rs.getInt("departmentID"), rs.getInt("employeeID"), rs.getInt("employeeAge"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;

	}

	@Override
	public boolean addEmployee(Employee employee) throws MessageException  {
		int rows = 0;
		if(employee.getAge() < 18) {
			throw new MessageException("Employee must be 18 years or older to work here!");
		}
		else {
			try {
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO employee (employeeName, departmentID, employeeAge)"
						+ " VALUES (?,?,?)");
				pstmt.setString(1, employee.getEmployeeName());
				pstmt.setInt(2, employee.getDepartmentID());
				pstmt.setInt(3, employee.getAge());
				rows = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rows == 1;

		}		
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		int rows= 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE employee SET employeeName= ?, departmentID = ?, employeeAge = ?  WHERE employeeID = ?");
			pstmt.setString(1, employee.getEmployeeName());
			pstmt.setInt(4, employee.getID());
			pstmt.setInt(2, employee.getDepartmentID());
			pstmt.setInt(3, employee.getAge());
			rows = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return rows ==1;
	}

	@Override
	public boolean deleteEmployee(int id) {
			int rows= 0;
		// TODO Auto-generated method stub
		try { 
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM employee WHERE employeeID = ?");
			pstmt.setInt(1, id);
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows ==1;

	}

}
