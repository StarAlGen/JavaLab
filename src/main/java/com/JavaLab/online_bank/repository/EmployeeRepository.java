package com.JavaLab.online_bank.repository;

import com.JavaLab.online_bank.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAllByBankOfficeId(Long id);
}