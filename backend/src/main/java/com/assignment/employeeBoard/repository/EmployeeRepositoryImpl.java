package com.assignment.employeeBoard.repository;

import com.assignment.employeeBoard.entity.Employee;
import com.assignment.employeeBoard.entity.EmployeeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper to map the ResultSet to the Employee object
    private RowMapper<Employee> rowMapper = (ResultSet rs, int rowNum) -> {
        Employee employee = new Employee();
        employee.setEmployeeId((rs.getLong("employeeId")));
        employee.setEmployeeFirstName(rs.getString("employeeFirstName"));
        employee.setEmployeeLastName(rs.getString("employeeLastName"));
        employee.setEmployeeSalary(rs.getFloat("employeeSalary"));
        employee.setEmployeePos(rs.getString("employeePos"));
        employee.setEmployeeStatus(EmployeeStatus.valueOf(rs.getString("employeeStatus")));
        return employee;
    };

    // Fetch all employees from the database
    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM Employee", rowMapper);
    }

    @Override
    public Employee findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Employee WHERE employeeId=?", rowMapper, id);
    }

    // Save a new employee record in the database
    @Override
    public Employee save(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Employee (employeeId, employeeFirstName, employeeLastName, employeeSalary, employeePos, employeeStatus) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, employee.getEmployeeId());
            ps.setString(2, employee.getEmployeeFirstName());
            ps.setString(3, employee.getEmployeeLastName());
            ps.setFloat(4, employee.getEmployeeSalary());
            ps.setString(5,employee.getEmployeePos());
            ps.setString(6,employee.getEmployeeStatus());
            return ps;
        }, keyHolder);

        Number key = (Number) keyHolder.getKey();
        if (key != null) {
            employee.setEmployeeId(key.longValue());
        }
        return employee;
    }

    @Override
    public void update(Employee employee) {
        jdbcTemplate.update("UPDATE Employee SET employeeFirstName=?, employeeLastName=?, employeeSalary=?, employeePos=?, employeeStatus=? WHERE employeeId=?",
                employee.getEmployeeFirstName(), employee.getEmployeeLastName(), employee.getEmployeeSalary(), employee.getEmployeeId(), employee.getEmployeePos(), employee.getEmployeeStatus());
    }

    @Override
    public boolean delete(Long id) {
        int rowAffected = jdbcTemplate.update("DELETE FROM Employee WHERE employeeId=?", id);
        return rowAffected > 0;
    }

    @Override
    public List<Employee> searchEmployees(Long id, String firstName, String lastName, Float salary, String pos) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Employee WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (id != null) {
            sql.append(" AND employeeId = ? ");
            params.add(id);
        }

        if (firstName != null && !firstName.isEmpty()) {
            sql.append(" AND employeeFirstName LIKE ? ");
            params.add("%" + firstName + "%");
        }

        if (lastName != null && !lastName.isEmpty()) {
            sql.append(" AND employeeLastName LIKE ? ");
            params.add("%" + lastName + "%");
        }
        if (salary != null) {
            sql.append(" AND employeeSalary = ? ");
            params.add(salary);
        }
        if (pos != null && !pos.isEmpty()) {
            sql.append(" AND employeePos = ? ");
            params.add(pos);
        }

        return jdbcTemplate.query(sql.toString(), (rs, rowNum) -> {
            Employee employee1 = new Employee();
            employee1.setEmployeeId(rs.getLong("employeeId"));
            employee1.setEmployeeFirstName(rs.getString("employeeFirstName"));
            employee1.setEmployeeLastName(rs.getString("employeeLastName"));
            employee1.setEmployeeSalary(rs.getFloat("employeeSalary"));
            employee1.setEmployeePos(rs.getString("employeePos"));
            return employee1;
        }, params.toArray());
    }

    @Override
    public Map<String, Object> analyze() {
        Map<String, Object> analysis = new HashMap<>();

        int totalEmployees = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Employee", Integer.class);
        analysis.put("totalEmployees", totalEmployees);

        Float maxSalary = jdbcTemplate.queryForObject("SELECT MAX(employeeSalary) FROM Employee", Float.class);
        analysis.put("maxSalary", maxSalary);

        Float minSalary = jdbcTemplate.queryForObject("SELECT MIN(employeeSalary) FROM Employee", Float.class);
        analysis.put("minSalary", minSalary);

        String avgQuery = "SELECT AVG(employeeSalary)  FROM Employee";
        Float avgSalary = jdbcTemplate.queryForObject(avgQuery, Float.class);
        analysis.put("avgSalary", avgSalary);

        return analysis;
    }

    @Override
    public List<Employee> getActiveEmployees() {
        return jdbcTemplate.query("SELECT * FROM Employee WHERE employeeStatus = 'ACTIVE'", new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public List<Employee> getInactiveEmployees() {
        return jdbcTemplate.query("SELECT * FROM Employee WHERE employeeStatus = 'INACTIVE'", new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public List<Employee> getOnLeaveEmployees() {
        return jdbcTemplate.query("SELECT * FROM Employee WHERE employeeStatus = 'ON_LEAVE'", new BeanPropertyRowMapper<>(Employee.class));
    }

}
