package tech.reliab.course.starodubovLab.bank.service;

import tech.reliab.course.starodubovLab.bank.entity.Bank;
import tech.reliab.course.starodubovLab.bank.entity.BankOffice;
import tech.reliab.course.starodubovLab.bank.entity.Employee;
import tech.reliab.course.starodubovLab.bank.entity.User;

import java.math.BigDecimal;

public interface BankService {
    Bank create(Bank bank);

    boolean addOffice(Bank bank, BankOffice bankOffice);

    boolean removeOffice (Bank bank, BankOffice bankOffice);

    boolean addEmployee(Bank bank, Employee employee);

    boolean removeEmployee(Bank bank, Employee employee);

    boolean addUser(Bank bank, User user);

    boolean removeUser(Bank bank, User user);

    boolean depositMoney(Bank bank, BigDecimal amount);

    boolean withdrawMoney(Bank bank, BigDecimal amount);
}
