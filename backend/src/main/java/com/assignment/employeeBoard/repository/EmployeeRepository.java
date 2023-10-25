package com.assignment.employeeBoard.repository;

import com.assignment.employeeBoard.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

//public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//}


public interface EmployeeRepository {
    List<Employee> findAll();
    Employee findById(Long id);
    Employee save(Employee employee);
    void update(Employee employee);
    boolean delete(Long id);

    List<Employee> searchEmployees(Long id, String firstName, String lastName, Float salary, String pos);

    Map<String, Object> analyze();

    List<Employee> getActiveEmployees();

    List<Employee> getInactiveEmployees();

    List<Employee> getOnLeaveEmployees();
}
