package tech.reliab.course.starodubovLab.bank.service;

import tech.reliab.course.starodubovLab.bank.entity.CreditAccount;

public interface CreditAccountService {
    CreditAccount create (CreditAccount creditAccount);

    boolean makeMonthlyPayment(CreditAccount creditAccount);
}
