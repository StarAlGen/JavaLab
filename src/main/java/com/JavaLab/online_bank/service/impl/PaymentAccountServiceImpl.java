package com.JavaLab.online_bank.service.impl;

import

import com.JavaLab.online_bank.entity.PaymentAccount;
import com.JavaLab.online_bank.repository.PaymentAccountRepository;
import com.JavaLab.online_bank.service.PaymentAccountService;
import com.JavaLab.online_bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Override
    public PaymentAccount create(PaymentAccount paymentAccount) {
        if (paymentAccount == null) {
            return null;
        }

        if (paymentAccount.getBalance().signum() < 0) {
            System.err.println("Error: PaymentAccount - payment account balance must be non-negative");
            return null;
        }

        return paymentAccountRepository.save(paymentAccount);
    }

    @Override
    public boolean depositMoney(PaymentAccount paymentAccount, BigDecimal amount) {
        if (paymentAccount == null) {
            System.err.println("Error: PaymentAccount - non existing payment account");
            return false;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: PaymentAccount - deposit amount must be positive");
            return false;
        }

        paymentAccount.setBalance(paymentAccount.getBalance().add(amount));
        paymentAccountRepository.save(paymentAccount);
        return true;
    }

    @Override
    public boolean withdrawMoney(PaymentAccount paymentAccount, BigDecimal amount) {
        if (paymentAccount == null) {
            System.err.println("Error: PaymentAccount - non existing payment account");
            return false;
        }

        if (amount.signum() <= 0) {
            System.err
                    .println("Error: PaymentAccount - withdrawal amount must be positive");
            return false;
        }

        if (paymentAccount.getBalance().compareTo(amount) < 0) {
            System.err.println("Error:PaymentAccount - not enough money");
            return false;
        }

        paymentAccount.setBalance(paymentAccount.getBalance().subtract(amount));
        paymentAccountRepository.save(paymentAccount);
        return true;
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccounts() {
        return paymentAccountRepository.findAll();
    }

    @Override
    public PaymentAccount getPaymentAccountById(Long id) {
        return paymentAccountRepository.findById(id).orElse(null);
    }

    @Override
    public void printPaymentData(Long id) {
        PaymentAccount account = getPaymentAccountById(id);
        if (account == null) {
            return;
        }

        System.out.println(account);
    }
}
