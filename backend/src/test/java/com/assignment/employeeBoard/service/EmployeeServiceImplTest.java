package com.assignment.employeeBoard.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.assignment.employeeBoard.entity.Employee;
import com.assignment.employeeBoard.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeFirstName("John");
        employee.setEmployeeLastName("Doe");
        employee.setEmployeeSalary(5000.0F);

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);

        assertNotNull(savedEmployee);
        assertEquals("John", savedEmployee.getEmployeeFirstName());
    }

    @Test
    public void testGetEmployeeById_whenEmployeeExists() {
        Employee employee = new Employee();
        employee.setEmployeeFirstName("John");
        employee.setEmployeeLastName("Doe");
        employee.setEmployeeSalary(5000.0F);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee foundEmployee = employeeService.getEmployeeById(1L);

        assertNotNull(foundEmployee);
        assertEquals("John", foundEmployee.getEmployeeFirstName());
    }

    @Test
    public void testGetEmployeeById_whenEmployeeDoesNotExist() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        Employee foundEmployee = employeeService.getEmployeeById(1L);

        assertNull(foundEmployee);
    }


}