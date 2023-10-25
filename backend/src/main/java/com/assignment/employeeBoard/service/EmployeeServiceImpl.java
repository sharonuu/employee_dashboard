package com.assignment.employeeBoard.service;

import com.assignment.employeeBoard.entity.Employee;
import com.assignment.employeeBoard.repository.EmployeeRepository;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee; // Note: This won't return the generated ID unless you modify the save method in the repository to do so.
    }

    @Override
    public List<Employee> fetchAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> searchEmployee(Long id, String firstName, String lastName, Float salary, String pos) {
        return employeeRepository.searchEmployees(id, firstName, lastName, salary, pos);
    }

    @Override
    public Map<String, Object> analyze() {
        return employeeRepository.analyze();
    }

    @Override
    public List<Employee> getActiveEmployees() {
        return employeeRepository.getActiveEmployees();
    }

    @Override
    public List<Employee> getInactiveEmployees() {
        return employeeRepository.getInactiveEmployees();
    }

    @Override
    public List<Employee> getOnLeaveEmployees() {
        return employeeRepository.getOnLeaveEmployees();
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee updatedEmployee) {
        Employee originalEmployee = employeeRepository.findById(id);
        if (originalEmployee != null) {
            updateIfNotNullOrEmpty(originalEmployee::setEmployeeFirstName, updatedEmployee.getEmployeeFirstName());
            updateIfNotNullOrEmpty(originalEmployee::setEmployeeLastName, updatedEmployee.getEmployeeLastName());
            updateIfNotNullOrEmpty(originalEmployee::setEmployeePos, updatedEmployee.getEmployeePos());
            if (updatedEmployee.getEmployeeSalary() != 0.0F) {
                originalEmployee.setEmployeeSalary(updatedEmployee.getEmployeeSalary());
            }
            if (updatedEmployee.getEmployeeStatus() != null) {
                originalEmployee.setEmployeeStatus(updatedEmployee.getEmployeeStatus());
            }
            employeeRepository.update(originalEmployee);
            return originalEmployee;
        }
        return null;
    }

    private void updateIfNotNullOrEmpty(Consumer<String> setter, String value) {
        if (value != null && !value.trim().isEmpty()) {
            setter.accept(value);
        }
    }

    @Override
    public String deleteEmployeeById(Long id) {
        Employee existingEmployee = employeeRepository.findById(id);
        if (existingEmployee != null) {
            employeeRepository.delete(id);
            return "Employee deleted successfully!";
        } else {
            return "No such employee in the database";
        }
    }
}
