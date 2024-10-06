package tech.reliab.course.starodubovLab.bank.service;

import tech.reliab.course.starodubovLab.bank.entity.BankOffice;
import tech.reliab.course.starodubovLab.bank.entity.Employee;

public interface EmployeeService {
    Employee create(Employee employee);

    boolean transferEmployee(Employee employee, BankOffice bankOffice);
}
