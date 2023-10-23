package com.assignment.employeeBoard.service;

import com.assignment.employeeBoard.entity.Employee;
import com.assignment.employeeBoard.repository.EmployeeRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> fetchAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee updateEmployeeById(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id)
                .map(originalEmployee -> {
                    updateIfNotNullOrEmpty(originalEmployee::setEmployeeFirstName, updatedEmployee.getEmployeeFirstName());
                    updateIfNotNullOrEmpty(originalEmployee::setEmployeeLastName, updatedEmployee.getEmployeeLastName());
                    if (updatedEmployee.getEmployeeSalary() != 0.0F) {
                        originalEmployee.setEmployeeSalary(updatedEmployee.getEmployeeSalary());
                    }
                    return employeeRepository.save(originalEmployee);
                })
                .orElse(null);
    }

    private void updateIfNotNullOrEmpty(Consumer<String> setter, String value) {
        if (value != null && !value.trim().isEmpty()) {
            setter.accept(value);
        }
    }

    @Override
    public String deleteEmployeeById(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return "Employee deleted successfully!";
        } else {
            return "No such employee in the database";
        }
    }
}
