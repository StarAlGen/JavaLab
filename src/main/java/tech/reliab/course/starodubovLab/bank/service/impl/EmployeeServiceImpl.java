package tech.reliab.course.starodubovLab.bank.service.impl;

import tech.reliab.course.starodubovLab.bank.entity.BankOffice;
import tech.reliab.course.starodubovLab.bank.entity.Employee;
import tech.reliab.course.starodubovLab.bank.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Employee create(Employee employee){
        if (employee == null){
            return null;
        }
        if (employee.getBank() == null){
            System.err.println("Error: Employee - no bank");
            return null;
        }
        if (employee.getBankOffice() == null){
            System.err.println("Error: Employee - no office");
            return null;
        }
        if (employee.getSalaryAmount().signum() <= 0){
            System.err.println("Error: Employee - salary should not be negative or equal to zero");
            return null;
        }
        return new Employee(employee);
    }

    @Override
    public boolean transferEmployee(Employee employee, BankOffice bankOffice){
        if (employee != null && bankOffice != null){
            if (employee.getBank() == bankOffice.getBank()){
                employee.setBankOffice(bankOffice);
            }
            else{
                employee.getBank().setEmployeeCount(employee.getBank().getEmployeeCount() - 1);
                employee.setBank(bankOffice.getBank());
                employee.setBankOffice(bankOffice);
            }
            return true;
        }
        return false;
    }
}
