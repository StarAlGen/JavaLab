package tech.reliab.course.starodubovLab.bank.service.impl;

import tech.reliab.course.starodubovLab.bank.entity.Bank;
import tech.reliab.course.starodubovLab.bank.entity.BankOffice;
import tech.reliab.course.starodubovLab.bank.entity.Employee;
import tech.reliab.course.starodubovLab.bank.entity.User;
import tech.reliab.course.starodubovLab.bank.service.BankService;

import java.math.BigDecimal;

public class BankServiceImpl implements BankService {

    @Override
    public Bank create(Bank bank){
        if(bank == null){
            return null;
        }
        return new Bank(bank);
    }

    @Override
    public boolean addOffice (Bank bank, BankOffice bankOffice){
        if (bank != null && bankOffice != null){
            bankOffice.setBank(bank);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeOffice(Bank bank, BankOffice bankOffice){
        if (bank != null && bankOffice != null){
            final int newOfficeCount = bank.getOfficeCount() - 1;

            if (newOfficeCount < 0) {
                System.err.println("Error: Bank - cannot remove office, no offices");
                return false;
            }

            bank.setOfficeCount(newOfficeCount);
            bankOffice.setBank(null);
            return true;
        }
        return false;
    }

    @Override
    public boolean addEmployee(Bank bank, Employee employee){
        if (bank != null && employee != null){
            employee.setBank(bank);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEmployee(Bank bank, Employee employee){
        if (bank != null && employee != null){
            int employeeCount = bank.getEmployeeCount() - 1;
            if (employeeCount < 0){
                System.err.println("Error: Bank - cannot remove employee, no employees");
                return false;
            }
            bank.setEmployeeCount(employeeCount);
            employee.setBank(null);
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(Bank bank, User user){
        if (bank != null && user != null){
            user.setBank(bank);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeUser(Bank bank, User user){
        if (bank != null && user != null){
            int userCount = bank.getUserCount() - 1;
            if (userCount < 0){
                System.err.println("Error: Bank - cannot remove user, no users");
                return false;
            }
            bank.setUserCount(userCount);
            user.setBank(null);
            return true;
        }
        return false;
    }

    @Override
    public boolean depositMoney(Bank bank, BigDecimal amount){
        if (bank == null){
            return false;
        }
        if (amount.signum() < 0){
            System.err.println("Error: Bank - cannot deposit negative amount");
            return false;
        }
        bank.setTotalMoney(bank.getTotalMoney().add(amount));
        return true;
    }

    @Override
    public boolean withdrawMoney(Bank bank, BigDecimal amount){
        if (bank == null){
            return false;
        }
        if (amount.signum() <= 0){
            System.err.println("Error: Bank - cannot withdraw non-positive amount");
            return false;
        }
        if (bank.getTotalMoney().compareTo(amount) < 0){
            System.err.println("Error: Bank - cannot withdraw money -- not enough funds");
            return false;
        }
        bank.setTotalMoney(bank.getTotalMoney().subtract(amount));
        return true;
    }
}
