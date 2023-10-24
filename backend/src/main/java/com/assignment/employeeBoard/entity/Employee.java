package com.assignment.employeeBoard.entity;

import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private Float employeeSalary;

    private String employeePos;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    public Employee() {
    }

    public Employee(Long employeeId, String employeeFirstName, String employeeLastName, Float employeeSalary) {
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeSalary = employeeSalary;
    }

    public Long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFirstName() {
        return this.employeeFirstName;
    }


    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return this.employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public float getEmployeeSalary() {
        return this.employeeSalary;
    }

    public void setEmployeeSalary(float employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeStatus() {
        return String.valueOf(this.employeeStatus);
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getEmployeePos() {
        return this.employeePos;
    }

    public void setEmployeePos(String employeePos) {
        this.employeePos = employeePos;
    }
}
