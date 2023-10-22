package com.assignment.employeeBoard.controller;

import com.assignment.employeeBoard.entity.Employee;
import com.assignment.employeeBoard.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
        origins = {"http://localhost:8081"}
)
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController() {
    }

    @PostMapping({"/employee"})
    public Employee saveEmployee(@RequestBody Employee employee) {
        return this.employeeService.saveEmployee(employee);
    }

    @GetMapping({"/employee"})
    public List<Employee> getAllEmployees() {
        return this.employeeService.fetchAllEmployees();
    }

    @GetMapping({"/employee/{id}"})
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return this.employeeService.getEmployeeById(id);
    }

    @PutMapping({"/employee/{id}"})
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return this.employeeService.updateEmployeeById(id, employee);
    }

    @DeleteMapping({"/employee/{id}"})
    public String deleteEmployee(@PathVariable("id") Long id) {
        return this.employeeService.deleteEmployeeById(id);
    }
}
