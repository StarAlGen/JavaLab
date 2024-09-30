package tech.reliab.course.starodubovLab.bank.service.impl;

import tech.reliab.course.starodubovLab.bank.entity.BankAtm;
import tech.reliab.course.starodubovLab.bank.entity.BankOffice;
import tech.reliab.course.starodubovLab.bank.entity.Employee;
import tech.reliab.course.starodubovLab.bank.service.BankOfficeService;

import java.math.BigDecimal;

public class BankOfficeServiceImpl implements BankOfficeService {

    @Override
    public BankOffice create (BankOffice bankOfficeService){
        if (bankOfficeService == null){
            return null;
        }

        if (bankOfficeService.getBank() == null){
            System.err.println("Error: BankOffice - must have bank");
            return null;
        }

        if (bankOfficeService.getTotalMoney().signum() < 0){
            System.err.println("Error: BankOffice - total money must be non-negative");
            return null;
        }
        if (bankOfficeService.getRentPrice().signum() < 0){
            System.err.println("Error: BankOffice - rentPrice must be non-negative");
            return null;
        }

        if (bankOfficeService.getTotalMoney().compareTo(bankOfficeService.getBank().getTotalMoney()) > 0){
            System.err.println("Error: BankOffice - There should be no more money in the bank office than in the bank itself");
            return null;
        }

        return new BankOffice(bankOfficeService);
    }

    @Override
    public boolean installAtm (BankOffice bankOffice, BankAtm bankAtm){
        if (bankOffice != null && bankAtm != null){
            if (!bankOffice.getIsAtmPlaceable()){
                System.err.println("Error: BankOffice - cannot install atm");
                return false;
            }
            bankAtm.setBankOffice(bankOffice);
            bankAtm.setAddress(bankOffice.getAddress());
            bankOffice.setAtmCount(bankOffice.getAtmCount() + 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAtm (BankOffice bankOffice, BankAtm bankAtm){
        if (bankOffice != null & bankAtm != null){
            if (bankOffice.getAtmCount() - 1 < 0){
                System.err.println("Error: BankOffice - cannot remove ATM, no ATMs");
                return false;
            }
            bankOffice.setAtmCount(bankOffice.getAtmCount() - 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean depositMoney (BankOffice bankOffice, BigDecimal amount){
        if (bankOffice == null){
            System.err.println("Error: BankOffice - cannot deposit money to not existing office");
            return false;
        }

        if (amount.signum() <= 0){
            System.err.println("Error: BankOffice - cannot deposit money - office cannot accept deposit");
            return false;
        }
        if (!bankOffice.getCashDepositAvailable()){
            System.err.println("Error: BankOffice - cannot deposit money - office cannot accept deposit");
            return false;
        }

        bankOffice.setTotalMoney(bankOffice.getTotalMoney().add(amount));
        return true;
    }

    @Override
    public boolean withdrawMoney (BankOffice bankOffice, BigDecimal amount){
        if (bankOffice == null){
            System.err.println("Error: BankOffice - cannot withdraw money from not existing office");
            return false;
        }

        if (amount.signum() < 0){
            System.err.println("Error: BankOffice - cannot withdraw money - withdraw amount must be positive");
            return false;
        }

        if (!bankOffice.getCashWithdrawalAvailable()){
            System.err.println("Error: BankOffice - cannot withdraw money - office cannot give withdrawal");
            return false;
        }
        if (bankOffice.getTotalMoney().compareTo(amount) < 0){
            System.err.println("Error: BankOffice - cannot withdraw money - office does not have enough money");
            return false;
        }
        bankOffice.setTotalMoney(bankOffice.getTotalMoney().subtract(amount));
        return true;
    }

    @Override
    public boolean addEmployee (BankOffice bankOffice, Employee employee) {
        if (bankOffice != null && employee != null){
            employee.setBankOffice(bankOffice);
            employee.setBank(bankOffice.getBank());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEmployee (BankOffice bankOffice, Employee employee){
        if (bankOffice != null && employee != null){
            if (employee.getBankOffice().getId() != bankOffice.getId()){
                return false;
            }
            employee.setBankOffice(null);
            employee.getBank().setEmployeeCount(employee.getBank().getEmployeeCount() - 1);
            return true;
        }
        return false;
    }
}