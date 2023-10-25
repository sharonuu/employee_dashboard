package com.assignment.employeeBoard.controller;

import com.assignment.employeeBoard.entity.Employee;
import com.assignment.employeeBoard.service.EmployeeService;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(
        origins = {"http://localhost:8081"}
)
public class EmployeeController {

    // Injecting the EmployeeService to handle business logic
    @Autowired
    private EmployeeService employeeService;

    // Default constructor
    public EmployeeController() {
    }

    /**
     * Endpoint to save a new employee.
     *
     * @param employee - The employee details to be saved.
     * @return - Returns the saved employee.
     */
    @PostMapping({"/save"})
    public Employee saveEmployee(@RequestBody Employee employee) {
        return this.employeeService.saveEmployee(employee);
    }

    /**
     * Endpoint to retrieve all employees.
     *
     * @return - Returns a list of all employees.
     */
    @GetMapping({"/"})
    public List<Employee> getAllEmployees() {
        return this.employeeService.fetchAllEmployees();
    }

    /**
     * Endpoint to retrieve an employee by ID.
     *
     * @param id - The ID of the employee to be retrieved.
     * @return - Returns the employee with the specified ID.
     */
    @GetMapping({"/{id}"})
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return this.employeeService.getEmployeeById(id);
    }

    /**
     * Endpoint to update an employee.
     *
     * @param id - The ID of the employee to be updated.
     * @param employee - The updated employee details.
     * @return - Returns the updated employee.
     */
    @PutMapping({"/{id}"})
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return this.employeeService.updateEmployeeById(id, employee);
    }

    /**
     * Endpoint to delete an employee.
     *
     * @param id - The ID of the employee to be deleted.
     * @return - Returns a message indicating the outcome of the deletion.
     */
    @DeleteMapping({"/{id}"})
    public String deleteEmployee(@PathVariable("id") Long id) {
        return this.employeeService.deleteEmployeeById(id);
    }

    /**
     * Endpoint to search for employees based on various criteria.
     *
     * @param parameters - Include employeeId, employeeFirstName, employeeLastName, employeeSalary, employeePos.
     * @return - Returns a list of employees matching the search criteria.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(
            @RequestParam(required = false) Long employeeId,
            @RequestParam(required = false) String employeeFirstName,
            @RequestParam(required = false) String employeeLastName,
            @RequestParam(required = false) Float employeeSalary,
            @RequestParam(required = false) String employeePos) {

        List<Employee> employees = employeeService.searchEmployee(employeeId, employeeFirstName, employeeLastName, employeeSalary, employeePos);
        return ResponseEntity.ok(employees);
    }

    /**
     * Endpoint to get a detailed analysis of employees.
     *
     * @return - Returns a map with analysis results.
     */
    @GetMapping("/analyze")
    public ResponseEntity<Map<String, Object>> analyzeEmployees() {
        Map<String, Object> analysis = employeeService.analyze();
        return ResponseEntity.ok(analysis);
    }

    /**
     * Endpoint to get a list of active employees.
     *
     * @return - Returns a list of active employees.
     */
    @GetMapping("/active")
    public ResponseEntity<List<Employee>> getActiveEmployees() {
        return ResponseEntity.ok(employeeService.getActiveEmployees());
    }

    /**
     * Endpoint to get a list of inactive employees.
     *
     * @return - Returns a list of inactive employees.
     */
    @GetMapping("/inactive")
    public ResponseEntity<List<Employee>> getInactiveEmployees() {
        return ResponseEntity.ok(employeeService.getInactiveEmployees());
    }

    /**
     * Endpoint to get a list of employees currently on leave.
     *
     * @return - Returns a list of employees on leave.
     */
    @GetMapping("/on_leave")
    public ResponseEntity<List<Employee>> getOnLeaveEmployees() {
        return ResponseEntity.ok(employeeService.getOnLeaveEmployees());
    }
}
