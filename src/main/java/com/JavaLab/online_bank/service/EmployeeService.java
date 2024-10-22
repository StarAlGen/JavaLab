package com.JavaLab.online_bank.service;

import com.JavaLab.online_bank.entity.BankOffice;
import com.JavaLab.online_bank.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);

    public Employee getEmployeeById(Long id);

    public List<Employee> getAllEmployees();

}
