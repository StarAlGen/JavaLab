package com.JavaLab.online_bank.service;

import com.JavaLab.online_bank.entity.*;

import java.math.BigDecimal;
import java.util.List;

public interface BankService {

    public Bank create(Bank bank);

    public Bank getBankById(Long bankId);


    public List<BankOffice> getAllOfficesByBankId(Long id);

    public void deleteBankById(Long bankId);

    public List<Bank> getAllBanks();

    public void printBankData(Long bankId);

    public boolean addOffice(Long bankId, BankOffice bankOffice);

    public boolean removeOffice(Long bankId, BankOffice bankOffice);

    public boolean addEmployee(Bank bank, Employee employee);

    public boolean removeEmployee(Bank bank, Employee employee);

    public boolean addUser(Long id, User user);

    public boolean removeUser(Bank bank, User user);

    public BigDecimal calculateInterestRate(Bank bank);

    public boolean depositMoney(Long id, BigDecimal amount);

    public boolean withdrawMoney(Long id, BigDecimal amount);

}
