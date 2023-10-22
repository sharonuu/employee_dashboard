package com.assignment.employeeBoard.service;

import com.assignment.employeeBoard.entity.Employee;
import com.assignment.employeeBoard.repository.EmployeeRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl() {
    }

    public Employee saveEmployee(Employee employee) {
        return (Employee)this.employeeRepository.save(employee);
    }

    public List<Employee> fetchAllEmployees() {
        List<Employee> allEmployee = this.employeeRepository.findAll();
        return allEmployee;
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = this.employeeRepository.findById(id);
        return employee.isPresent() ? (Employee)employee.get() : null;
    }

    public Employee updateEmployeeById(Long id, Employee employee) {
        Optional<Employee> employee1 = this.employeeRepository.findById(id);
        if (employee1.isPresent()) {
            Employee originalEmployee = (Employee)employee1.get();
            if (Objects.nonNull(employee.getEmployeeFirstName()) && !"".equalsIgnoreCase(employee.getEmployeeFirstName())) {
                originalEmployee.setEmployeeFirstName(employee.getEmployeeFirstName());
            }

            if (Objects.nonNull(employee.getEmployeeLastName()) && !"".equalsIgnoreCase(employee.getEmployeeLastName())) {
                originalEmployee.setEmployeeLastName(employee.getEmployeeLastName());
            }

            if (Objects.nonNull(employee.getEmployeeSalary()) && employee.getEmployeeSalary() != 0.0F) {
                originalEmployee.setEmployeeSalary(employee.getEmployeeSalary());
            }

            return (Employee)this.employeeRepository.save(originalEmployee);
        } else {
            return null;
        }
    }

    public String deleteEmployeeById(Long id) {
        if (this.employeeRepository.findById(id).isPresent()) {
            this.employeeRepository.deleteById(id);
            return "Employee deleted successfully!";
        } else {
            return "No such employee in the database";
        }
    }
}
