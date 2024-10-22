package com.JavaLab.online_bank.service.impl;

import com.JavaLab.online_bank.entity.Bank;
import com.JavaLab.online_bank.entity.BankOffice;
import com.JavaLab.online_bank.entity.Employee;
import com.JavaLab.online_bank.entity.User;
import com.JavaLab.online_bank.repository.BankOfficeRepository;
import com.JavaLab.online_bank.repository.BankRepository;
import com.JavaLab.online_bank.repository.EmployeeRepository;
import com.JavaLab.online_bank.repository.UserRepository;
import com.JavaLab.online_bank.service.BankOfficeService;
import com.JavaLab.online_bank.service.BankService;
import com.JavaLab.online_bank.service.UserService;
import com.JavaLab.online_bank.utils.BigRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class BankServiceImpl implements BankService {
    private BankOfficeService bankOfficeService;
    private UserService userService;
    @Autowired
    private BankOfficeRepository bankOfficeRepository;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<BankOffice> getAllOfficesByBankId(Long id) {
        Bank bank = getBankById(id);
        if (bank != null) {
            return bankOfficeRepository.findAllBankOfficesByBankId(id);
        }
        return null;
    }

    @Override
    public Bank create(Bank bank) {
        if (bank == null) {
            return null;
        }
        return bankRepository.save(bank);
    }

    @Override
    public boolean addEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            employee.setBank(bank);
            bank.setEmployeeCount(bank.getEmployeeCount() + 1);
            employeeRepository.save(employee);
            bankRepository.save(bank);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            final int newEmployeeCount = bank.getEmployeeCount() - 1;

            if (newEmployeeCount < 0) {
                System.err.println("Error: Bank - cannot remove employee, no employees");
                return false;
            }

            bank.setEmployeeCount(newEmployeeCount);
            employeeRepository.deleteById(employee.getId());
            bankRepository.save(bank);

            return true;
        }
        return false;
    }

    @Override
    public Bank getBankById(Long bankId) {
        return bankRepository.findById(bankId).orElse(null);
    }

    @Override
    public void printBankData(Long bankId) {
        Bank bank = getBankById(bankId);
        if (bank == null) {
            return;
        }
        System.out.println("=====================");
        System.out.println(bank);

        List<BankOffice> offices = getAllOfficesByBankId(bankId);
        if (offices != null) {
            System.out.println("Offices:");
            offices.forEach((BankOffice office) -> {
                bankOfficeService.printBankOfficeData(office.getId());
            });
        }
        List<User> users = userRepository.findAllUsersByBankId(bankId);
        if (users != null) {
            System.out.println("users:");
            users.forEach((User user) -> {
                userService.printUserData(user.getId(), false);
            });
        }
        System.out.println("=====================");
    }

    @Override
    public void deleteBankById(Long bankId) {
        bankRepository.deleteById(bankId);
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public boolean addOffice(Long bankId, BankOffice bankOffice) {
        Bank bank = getBankById(bankId);
        if (bank != null && bankOffice != null) {
            bankOffice.setBank(bank);
            bank.setOfficeCount(bank.getOfficeCount() + 1);
            bank.setAtmCount(bank.getAtmCount() + bankOffice.getAtmCount());
            depositMoney(bankId, bankOffice.getTotalMoney());
            bankRepository.save(bank);
            bankOfficeRepository.save(bankOffice);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeOffice(Long bankId, BankOffice bankOffice) {
        Bank bank = getBankById(bankId);
        int officeIndex = bankOfficeRepository.findAllBankOfficesByBankId(bankId).indexOf(bankOffice);
        if (bank != null && officeIndex >= 0) {
            final int newOfficeCount = bank.getOfficeCount() - 1;

            if (newOfficeCount < 0) {
                System.err.println("Error: Bank - cannot remove office, no offices");
                return false;
            }

            bank.setOfficeCount(newOfficeCount);

            bank.setAtmCount(bank.getAtmCount() - bankOffice.getAtmCount());
            bankOfficeRepository.deleteById(bankOffice.getId());
            bankRepository.save(bank);
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(Long id, User user) {
        Bank bank = getBankById(id);
        if (bank != null && user != null) {
            user.setBank(bank);
            bank.setUserCount(bank.getUserCount() + 1);
            userRepository.save(user);
            bankRepository.save(bank);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeUser(Bank bank, User user) {
        if (bank != null && user != null) {
            int newUserCount = bank.getUserCount() - 1;

            if (newUserCount < 0) {
                System.err.println("Error: Bank - cannot remove user, no users");
                return false;
            }

            bank.setUserCount(newUserCount);
            userRepository.deleteById(user.getId());
            bankRepository.save(bank);
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal calculateInterestRate(Bank bank) {
        if (bank != null) {
            final BigDecimal rating = BigDecimal.valueOf(bank.getRating());

            final BigDecimal centralBankInterestRate = BigRandom.between(new BigDecimal("0.0"), new BigDecimal("1.0"))
                    .multiply(Bank.MAX_INTEREST_RATE);
            final BigDecimal maxBankInterestRateMargin = Bank.MAX_INTEREST_RATE.subtract(centralBankInterestRate);
            final BigDecimal bankInterestRateMargin = (BigRandom.between(new BigDecimal("0.0"), new BigDecimal("1.0"))
                    .multiply(maxBankInterestRateMargin))
                    .multiply((new BigDecimal("110").subtract(rating).divide(new BigDecimal("100"))));
            final BigDecimal interestRate = centralBankInterestRate.add(bankInterestRateMargin);

            bank.setInterestRate(interestRate);
            bankRepository.save(bank);
            return interestRate;
        }
        return new BigDecimal("0");
    }

    @Override
    public boolean depositMoney(Long id, BigDecimal amount) {
        Bank bank = getBankById(id);
        if (bank == null) {
            System.err.println("Error: Bank - cannot deposit money to uninitialized bank");
            return false;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: Bank - cannot deposit money - deposit amount must be positive");
            return false;
        }

        bank.setTotalMoney(bank.getTotalMoney().add(amount));
        bankRepository.save(bank);
        return true;
    }

    @Override
    public boolean withdrawMoney(Long id, BigDecimal amount) {
        Bank bank = getBankById(id);
        if (bank == null) {
            System.err.println("Error: Bank - cannot withdraw money, bank is null");
            return false;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: Bank - cannot withdraw money - withdraw amount must be positive");
            return false;
        }

        if (bank.getTotalMoney().compareTo(amount) < 0) {
            System.err.println("Error: Bank - cannot withdraw money - bank does not have enough money");
            return false;
        }

        bank.setTotalMoney(bank.getTotalMoney().subtract(amount));
        bankRepository.save(bank);
        return true;

    }
}
