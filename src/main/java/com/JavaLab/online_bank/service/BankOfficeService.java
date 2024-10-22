package com.JavaLab.online_bank.service;

import com.JavaLab.online_bank.entity.BankAtm;
import com.JavaLab.online_bank.entity.BankOffice;
import com.JavaLab.online_bank.entity.Employee;
import lombok.extern.java.Log;

import java.math.BigDecimal;
import java.util.List;

public interface BankOfficeService {
    BankOffice create(BankOffice bankOffice);

    public void printBankOfficeData(Long id);

    public BankOffice getBankOfficeById(Long id);

    public List<BankOffice> getAllOffices();

    public List<Employee> getAllEmployeesByOfficeId(Long id);

    boolean installAtm(Long id, BankAtm bankAtm);

    boolean removeAtm(BankOffice bankOffice, BankAtm bankAtm);

    boolean depositMoney(BankOffice bankOffice, BigDecimal amount);

    boolean withdrawMoney(BankOffice bankOffice, BigDecimal amount);

    boolean addEmployee(Long id, Employee employee);

    boolean removeEmployee(BankOffice bankOffice, Employee employee);
}
