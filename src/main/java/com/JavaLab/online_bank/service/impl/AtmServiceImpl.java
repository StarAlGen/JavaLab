package com.JavaLab.online_bank.service.impl;

import com.JavaLab.online_bank.entity.BankAtm;
import com.JavaLab.online_bank.repository.BankAtmRepository;
import com.JavaLab.online_bank.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AtmServiceImpl implements AtmService {
    @Autowired
    private BankAtmRepository bankAtmRepository;

    @Override
    public BankAtm create(BankAtm bankAtm) {
        return bankAtmRepository.save(bankAtm);
    }

    @Override
    public void delete (Long id){
        bankAtmRepository.deleteById(id);
    }

    @Override
    public List<BankAtm> getAllBankAtms() {
        return bankAtmRepository.findAll();
    }

    @Override
    public BankAtm getBankAtmById(Long id) {
        return bankAtmRepository.findById(id).orElse(null);
    }

    @Override
    public boolean depositMoney(BankAtm bankAtm, BigDecimal amount) {
        if (bankAtm == null) {
            System.err.println("Error: BankAtm cannot deposit money - non existing ATM");
            return false;
        }
        if (amount.signum() <= 0) {
            System.err.println("Error: BankAtm cannot deposit money - amount is not positive");
            return false;
        }
        if (!bankAtm.isCashDepositAvailable()) {
            System.err.println("Error: BankAtm cannot deposit money - deposit is not allowed");
            return false;
        }
        bankAtm.setTotalMoney(bankAtm.getTotalMoney().add(amount));
        bankAtmRepository.save(bankAtm);
        return true;
    }

    @Override
    public boolean withdrawMoney(BankAtm bankAtm, BigDecimal amount) {
        if (bankAtm == null) {
            System.err.println("Error: BankAtm cannot withdraw money - non existing ATM");
            return false;
        }
        if (amount.signum() <= 0) {
            System.err.println("Error: BankAtm cannot withdraw money - amount is not positive");
            return false;
        }
        if (!bankAtm.isCashDepositAvailable()) {
            System.err.println("Error: BankAtm cannot withdraw money - deposit is not allowed");
            return false;
        }
        if (bankAtm.getTotalMoney().compareTo(amount) < 0) {
            System.err.println("Error: BankAtm cannot withdraw money - ATM does not have enough money");
            return false;
        }
        bankAtm.setTotalMoney(bankAtm.getTotalMoney().subtract(amount));
        bankAtmRepository.save(bankAtm);
        return true;
    }
}
