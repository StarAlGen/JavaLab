package tech.reliab.course.starodubovLab.bank.service;

import tech.reliab.course.starodubovLab.bank.entity.CreditAccount;

import java.util.List;

public interface CreditAccountService {
    CreditAccount create(CreditAccount creditAccount);

    public CreditAccount getCreditAccountById(int id);

    public List<CreditAccount> getAllCreditAccounts();

    boolean makeMonthlyPayment(CreditAccount creditAccount);
}
