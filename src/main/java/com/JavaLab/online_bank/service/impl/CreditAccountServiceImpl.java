package com.JavaLab.online_bank.service.impl;

import com.JavaLab.online_bank.entity.CreditAccount;
import com.JavaLab.online_bank.repository.CreditAccountRepository;
import com.JavaLab.online_bank.service.CreditAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class CreditAccountServiceImpl implements CreditAccountService {
    @Autowired
    private CreditAccountRepository creditAccountRepository;

    @Override
    public CreditAccount create(CreditAccount creditAccount) {
        if (creditAccount == null) {
            return null;
        }

        if (creditAccount.getMonthCount() < 1) {
            System.err.println("Error: CreditAccount - monthCount must be at least 1");
            return null;
        }

        if (creditAccount.getCreditAmount().signum() <= 0) {
            System.err.println("Error: CreditAccount - creditAmount must be positive");
            return null;
        }

        if (creditAccount.getBank() == null) {
            System.err.println("Error: CreditAccount - no bank");
            return null;
        }


        return creditAccountRepository.save(creditAccount);
    }

    @Override
    public boolean makeMonthlyPayment(CreditAccount creditAccount) {
        if (creditAccount == null || creditAccount.getPaymentAccount() == null) {
            System.err.println("Error: CreditAccount - no account to take money from");
            return false;
        }

        final BigDecimal monthlyPayment = creditAccount.getMonthlyPayment();
        final BigDecimal paymentAccountBalance = creditAccount.getPaymentAccount().getBalance();

        if (paymentAccountBalance.compareTo(monthlyPayment) < 0) {
            System.err.println("Error: CreditAccount - not enough balance for monthly payment");
            return false;
        }

        creditAccount.getPaymentAccount().setBalance(paymentAccountBalance.subtract(monthlyPayment));
        creditAccount.setRemainingCreditAmount(creditAccount.getRemainingCreditAmount().subtract(monthlyPayment));
        creditAccountRepository.save(creditAccount);
        return true;
    }

    @Override
    public List<CreditAccount> getAllCreditAccounts() {
        return creditAccountRepository.findAll();
    }

    @Override
    public CreditAccount getCreditAccountById(Long id) {
        return creditAccountRepository.findById(id).orElse(null);
    }
}
