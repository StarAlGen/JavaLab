package tech.reliab.course.starodubovLab.bank.service;

import tech.reliab.course.starodubovLab.bank.entity.BankAtm;
import tech.reliab.course.starodubovLab.bank.entity.BankOffice;
import tech.reliab.course.starodubovLab.bank.entity.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface BankOfficeService {
    BankOffice create(BankOffice bankOffice);

    public void printBankOfficeData(int id);

    public BankOffice getBankOfficeById(int id);

    public List<BankOffice> getAllOffices();

    public List<Employee> getAllEmployeesByOfficeId(int id);

    boolean installAtm(int id, BankAtm bankAtm);

    boolean removeAtm(BankOffice bankOffice, BankAtm bankAtm);

    boolean depositMoney(BankOffice bankOffice, BigDecimal amount);

    boolean withdrawMoney(BankOffice bankOffice, BigDecimal amount);

    boolean addEmployee(int id, Employee employee);

    boolean removeEmployee(BankOffice bankOffice, Employee employee);
}
