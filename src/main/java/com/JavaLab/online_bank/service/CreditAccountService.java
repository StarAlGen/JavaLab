package com.JavaLab.online_bank.service;

import com.JavaLab.online_bank.entity.CreditAccount;

import java.util.List;

public interface CreditAccountService {
    CreditAccount create(CreditAccount creditAccount);

    public CreditAccount getCreditAccountById(Long id);

    public List<CreditAccount> getAllCreditAccounts();

    boolean makeMonthlyPayment(CreditAccount creditAccount);
}
