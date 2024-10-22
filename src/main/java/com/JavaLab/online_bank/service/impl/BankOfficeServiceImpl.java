package com.JavaLab.online_bank.service.impl;

import com.JavaLab.online_bank.entity.BankAtm;
import com.JavaLab.online_bank.entity.BankOffice;
import com.JavaLab.online_bank.entity.Employee;
import com.JavaLab.online_bank.repository.BankAtmRepository;
import com.JavaLab.online_bank.repository.BankOfficeRepository;
import com.JavaLab.online_bank.repository.EmployeeRepository;
import com.JavaLab.online_bank.service.BankOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BankOfficeServiceImpl implements BankOfficeService {
    @Autowired
    private BankOfficeRepository bankOfficeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BankAtmRepository bankAtmRepository;

    @Override
    public List<Employee> getAllEmployeesByOfficeId(Long id) {
        return employeeRepository.findAllByBankOfficeId(id);
    }

    @Override
    public List<BankOffice> getAllOffices() {
        return bankOfficeRepository.findAll();
    }

    @Override
    public BankOffice getBankOfficeById(Long id) {
        return bankOfficeRepository.findById(id).orElse(null);
    }

    @Override
    public void printBankOfficeData(Long id) {
        BankOffice bankOffice = getBankOfficeById(id);
        if (bankOffice == null) {
            return;
        }
        System.out.println("=====================");
        System.out.println(bankOffice);
        List<Employee> employees = getAllEmployeesByOfficeId(id);
        if (employees != null) {
            System.out.println("Employees:");
            employees.forEach(System.out::println);
        }
        List<BankAtm> atms = bankAtmRepository.findAllBankAtmsByBankOfficeId(id);
        if (atms != null) {
            System.out.println("Atms:");
            atms.forEach(System.out::println);
        }
        System.out.println("=====================");
    }

    @Override
    public BankOffice create(BankOffice bankOffice) {
        if (bankOffice == null) {
            return null;
        }

        if (bankOffice.getTotalMoney().signum() < 0) {
            System.err.println("Error: BankOffice - total money must be non-negative");
            return null;
        }

        if (bankOffice.getBank() == null) {
            System.err.println("Error: BankOffice - must have bank");
            return null;
        }

        if (bankOffice.getRentPrice().signum() < 0) {
            System.err.println("Error: BankOffice - rentPrice must be non-negative");
            return null;
        }

        return bankOfficeRepository.save(bankOffice);
    }

    @Override
    public boolean depositMoney(BankOffice bankOffice, BigDecimal amount) {
        if (bankOffice == null) {
            System.err.println("Error: BankOffice - cannot deposit money to not existing office");
            return false;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: BankOffice - cannot deposit money - deposit amount must be positive");
            return false;
        }

        if (!bankOffice.isCashDepositAvailable()) {
            System.err.println("Error: BankOffice - cannot deposit money - office cannot accept deposit");
            return false;
        }

        bankOffice.setTotalMoney(bankOffice.getTotalMoney().add(amount));
        bankOfficeRepository.save(bankOffice);
        return true;
    }

    @Override
    public boolean withdrawMoney(BankOffice bankOffice, BigDecimal amount) {
        if (bankOffice == null) {
            System.err.println("Error: BankOffice - cannot withdraw money from not existing office");
            return false;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: BankOffice - cannot withdraw money - withdraw amount must be positive");
            return false;
        }

        if (!bankOffice.isCashWithdrawalAvailable()) {
            System.err.println("Error: BankOffice - cannot withdraw money - office cannot give withdrawal");
            return false;
        }

        if (bankOffice.getTotalMoney().compareTo(amount) < 0) {
            System.err.println("Error: BankOffice - cannot withdraw money - office does not have enough money");
            return false;
        }

        bankOffice.setTotalMoney(bankOffice.getTotalMoney().subtract(amount));
        bankOfficeRepository.save(bankOffice);
        return true;
    }

    @Override
    public boolean installAtm(Long id, BankAtm bankAtm) {
        BankOffice bankOffice = getBankOfficeById(id);
        if (bankOffice != null && bankAtm != null) {
            if (!bankOffice.isAtmPlaceable()) {
                System.err.println("Error: BankOffice - cannot install atm");
                return false;
            }

            bankOffice.setAtmCount(bankOffice.getAtmCount() + 1);
            bankOffice.getBank().setAtmCount(bankOffice.getBank().getAtmCount() + 1);
            bankAtm.setBankOffice(bankOffice);
            bankAtm.setAddress(bankOffice.getAddress());
            bankAtm.setBank(bankOffice.getBank());
            bankAtmRepository.save(bankAtm);
            bankOfficeRepository.save(bankOffice);

            return true;
        }
        return false;
    }

    @Override
    public boolean removeAtm(BankOffice bankOffice, BankAtm bankAtm) {
        if (bankOffice != null && bankAtm != null) {
            final int newAtmCountOffice = bankOffice.getAtmCount() - 1;
            if (newAtmCountOffice < 0) {
                System.err.println("Error: BankOffice - cannot remove ATM, no ATMs");
                return false;
            }

            bankOffice.setAtmCount(newAtmCountOffice);
            bankAtmRepository.deleteById(bankAtm.getId());
            bankOfficeRepository.save(bankOffice);
            return true;
        }
        return false;
    }

    @Override
    public boolean addEmployee(Long id, Employee employee) {
        BankOffice bankOffice = getBankOfficeById(id);
        if (bankOffice != null && employee != null) {
            employee.setBankOffice(bankOffice);
            employee.setBank(bankOffice.getBank());
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEmployee(BankOffice bankOffice, Employee employee) {
        if (bankOffice != null && employee != null){
            employeeRepository.deleteById(employee.getId());
            return true;
        }
        return false;
    }
}
