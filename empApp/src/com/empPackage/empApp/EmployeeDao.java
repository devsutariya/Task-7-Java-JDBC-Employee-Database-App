package com.empPackage.empApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public void addEmployee(Employee emp) {
        String query = "INSERT INTO employees (id, name, salary) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, emp.getId());
            ps.setString(2, emp.getName());
            ps.setDouble(3, emp.getSalary());
            ps.executeUpdate();
            System.out.println("Employee added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    public List<Employee> viewEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getDouble("salary")));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching employees: " + e.getMessage());
        }
        return employees;
    }

    public void updateEmployeeSalary(int id, double salary) {
        String query = "UPDATE employees SET salary = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setDouble(1, salary);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee salary updated.");
            } else {
                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating salary: " + e.getMessage());
        }
    }

    public void deleteEmployee(int id) {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }
}

