package tech.reliab.course.starodubovLab.bank.service;

import tech.reliab.course.starodubovLab.bank.entity.*;

import java.math.BigDecimal;
import java.util.List;

public interface BankService {

    public Bank create(Bank bank);

    public Bank getBankById(int bankId);

    public void setBankOfficeService(BankOfficeService bankOfficeService);

    public List<BankOffice> getAllOfficesByBankId(int id);

    public void setClientService(UserService bankOfficeService);

    public boolean deleteBankById(int bankId);

    public List<Bank> getAllBanks();

    public void printBankData(int bankId);

    public boolean addOffice(int bankId, BankOffice bankOffice);

    public boolean removeOffice(int bankId, BankOffice bankOffice);

    public boolean addEmployee(Bank bank, Employee employee);

    public boolean removeEmployee(Bank bank, Employee employee);

    public boolean addClient(int id, User client);

    public boolean removeClient(Bank bank, User client);

    public BigDecimal calculateInterestRate(Bank bank);

    public boolean depositMoney(int id, BigDecimal amount);

    public boolean withdrawMoney(int id, BigDecimal amount);

    public boolean approveCredit(Bank bank, CreditAccount account, Employee employee);
}
