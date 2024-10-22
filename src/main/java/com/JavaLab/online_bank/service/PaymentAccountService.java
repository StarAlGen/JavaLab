package com.JavaLab.online_bank.service;

import com.JavaLab.online_bank.entity.PaymentAccount;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentAccountService {
    PaymentAccount create(PaymentAccount paymentAccount);

    public void printPaymentData(Long id);

    public PaymentAccount getPaymentAccountById(Long id);

    public List<PaymentAccount> getAllPaymentAccounts();

    boolean depositMoney(PaymentAccount paymentAccount, BigDecimal amount);

    boolean withdrawMoney(PaymentAccount paymentAccount, BigDecimal amount);
}
