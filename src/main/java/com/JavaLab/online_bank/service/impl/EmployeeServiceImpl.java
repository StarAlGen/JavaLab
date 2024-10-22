package com.JavaLab.online_bank.service.impl;

import com.JavaLab.online_bank.entity.Employee;
import com.JavaLab.online_bank.repository.EmployeeRepository;
import com.JavaLab.online_bank.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        if (employee == null) {
            return null;
        }

        if (employee.getSalary().signum() < 0) {
            System.err.println("Error: Employee - salary must be non-negative");
            return null;
        }

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
