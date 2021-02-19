package com.cognixia.advancedjava.ems.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cognixia.advancedjava.ems.models.Department;
import com.cognixia.advancedjava.ems.models.Employee;
import com.cognixia.advancedjava.ems.models.MessageException;

public class EMSRunner {
	private static List<Department> deptList;
	private static DepartmentDAOClass deptDAO;
	private static EmployeeDAOClass empDAO;

	public EMSRunner() {
		
		
	}
	
	public static void main(String[] args) {
		deptList = new ArrayList<Department>();
		deptDAO = new DepartmentDAOClass();
		empDAO = new EmployeeDAOClass();
		
		System.out.println("Hello User, welcome to the Employee Managment System...");
		Scanner scan = new Scanner(System.in);

		while(true) {
			System.out.println("\nMAIN Page\n-------------------");
			System.out.println("What would you like to do");
			System.out.println("1.Access Departments");
			System.out.println("2.Access Employees");
			System.out.println("3.Exit program");
			
			int userChoice = scan.nextInt();
			if(userChoice == 1) {
				while(true){
					System.out.println("\nDEPARTMENTS\n------------------");
					System.out.println("1.Add a department");
					System.out.println("2.Remove a department");
					System.out.println("3.Update a department information");
					System.out.println("4.List a deparment");
					System.out.println("5.List all deparmtents");
					System.out.println("6.Return to main page");
					
					int userSecondChoice = scan.nextInt();
					if(userSecondChoice == 1) {
						scan.nextLine();
						System.out.print("Add Department Name: ");
						String depName = scan.nextLine();
						System.out.print("Add Department Phone Number: ");
						String depNumber = scan.nextLine();
						
						deptDAO.addDepartment(new Department(0, depName, depNumber, null));
						
					}
					else if(userSecondChoice == 2) {
						scan.nextLine();
						System.out.print("What is the ID of the deparmtnet you would like to remove: ");
						int depID = scan.nextInt();
						deptDAO.deleteDepartment(depID);
					}
					else if(userSecondChoice == 3) {
						scan.nextLine();
						System.out.print("What is the ID of the deparmtnet you would like to update: ");
						int depID = scan.nextInt();
						scan.nextLine();
						System.out.print("New Department Name: ");
						String depName = scan.nextLine();
						System.out.print("New Department Phone Number: ");
						String depNum = scan.nextLine();
						deptDAO.updateDepartment(new Department(depID, depName, depNum, null));
					}
					else if(userSecondChoice == 4) {
						scan.nextLine();
						System.out.print("What is the ID of the deparmtnet you wopuld like to view: ");
						int depID = scan.nextInt();
						System.out.println(deptDAO.getDepartment(depID));
					}
					else if(userSecondChoice == 5) {
						System.out.println(deptDAO.getAllDepartments());
					}
					else if(userSecondChoice == 6) {
						break;
					}
					else {
						System.out.println("Not a valid choice");
					}	
				}
			}
			else if(userChoice == 2) {
				
				while(true) {
					System.out.println("\nEMPLOYEE'S\n------------------");
					System.out.println("1.Add an employee");
					System.out.println("2.Remove an employee");
					System.out.println("3.Update an employee's information");
					System.out.println("4.List an employee");
					System.out.println("5.List all employees");
					System.out.println("6.Return to main page");

					int userSecondChoice = scan.nextInt();
					if(userSecondChoice == 1) {
						scan.nextLine();
						
						System.out.print("Employee Name: ");
						String empName = scan.nextLine();
						System.out.print("Employee age: ");
						int empAge = scan.nextInt();
						System.out.print("Employee's Department ID: ");
						int empDepID = scan.nextInt();
						
						try {
							empDAO.addEmployee(new Employee(empName,empDepID, 0, empAge));
						} catch (MessageException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(userSecondChoice == 2) {
						scan.nextLine();
						System.out.print("What is the ID of the emplopyee you would like to remove: ");
						int empID = scan.nextInt();
						empDAO.deleteEmployee(empID);
					}
					else if(userSecondChoice == 3) {
						scan.nextLine();
						
						System.out.print("What is the ID of the employee you would like to update: ");
						int empID = scan.nextInt();
						scan.nextLine();
						System.out.print("New Employee Name: ");
						String empName = scan.nextLine();
						System.out.print("New Age: ");
						int empAge = scan.nextInt();
						System.out.print("New Employee Department ID: ");
						int empDepID = scan.nextInt();
						
						empDAO.updateEmployee(new Employee(empName, empID, empDepID, empAge));						
						
					}
					else if(userSecondChoice == 4) {
						scan.nextLine();
						System.out.print("What is the ID of the employee you would like to view: ");
						int empID = scan.nextInt();
						System.out.println(empDAO.getEmployee(empID));
					}
					else if(userSecondChoice == 5) {
						System.out.println(empDAO.getEmployees());
					}
					else if(userSecondChoice == 6) {
						break;
					}
					else {
						System.out.println("Not a valid choice");
					}
				}
			}
			else if(userChoice == 3) {
				System.out.println("Exiting program...");
				System.exit(0);
			}
			else {
				System.out.println(userChoice + " is not a valid choice");
			}	
		}
		
	}

}
