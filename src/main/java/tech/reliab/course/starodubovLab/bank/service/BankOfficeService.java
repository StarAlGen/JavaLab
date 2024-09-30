package tech.reliab.course.starodubovLab.bank.service;

import tech.reliab.course.starodubovLab.bank.entity.BankAtm;
import tech.reliab.course.starodubovLab.bank.entity.BankOffice;
import tech.reliab.course.starodubovLab.bank.entity.Employee;

import java.math.BigDecimal;

public interface BankOfficeService {
    BankOffice create (BankOffice bankOffice);

    boolean installAtm (BankOffice bankOffice, BankAtm bankAtm);

    boolean removeAtm (BankOffice bankOffice, BankAtm bankAtm);

    boolean depositMoney(BankOffice bankOffice, BigDecimal amount);

    boolean withdrawMoney(BankOffice bankOffice, BigDecimal amount);

    boolean addEmployee(BankOffice bankOffice, Employee employee);

    boolean removeEmployee(BankOffice bankOffice, Employee employee);
}
