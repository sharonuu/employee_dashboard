package com.assignment.employeeBoard.service;

import com.assignment.employeeBoard.entity.Employee;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> fetchAllEmployees();

    Employee updateEmployeeById(Long id, Employee employee);

    String deleteEmployeeById(Long id);

    Employee getEmployeeById(Long id);

    List<Employee> searchEmployee(Long id, String firstName, String lastName, Float salary, String pos);

    Map<String, Object> analyze();

    List<Employee> getActiveEmployees();

    List<Employee> getInactiveEmployees();

    List<Employee> getOnLeaveEmployees();
}

