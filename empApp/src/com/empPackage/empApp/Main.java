package com.empPackage.empApp;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Employee Database App ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee Salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    dao.addEmployee(new Employee(id, name, salary));
                    break;

                case 2:
                    List<Employee> employees = dao.viewEmployees();
                    if (employees.isEmpty()) {
                        System.out.println("No employees found.");
                    } else {
                        for (Employee e : employees) {
                            System.out.println(e.getId() + " | " + e.getName() + " | " + e.getSalary());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Employee ID: ");
                    int uid = sc.nextInt();
                    System.out.print("Enter New Salary: ");
                    double newSalary = sc.nextDouble();
                    dao.updateEmployeeSalary(uid, newSalary);
                    break;

                case 4:
                    System.out.print("Enter Employee ID: ");
                    int did = sc.nextInt();
                    dao.deleteEmployee(did);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
