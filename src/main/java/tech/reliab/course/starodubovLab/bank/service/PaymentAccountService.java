package tech.reliab.course.starodubovLab.bank.service;

import tech.reliab.course.starodubovLab.bank.entity.PaymentAccount;

import java.math.BigDecimal;

public interface PaymentAccountService {
    PaymentAccount create(PaymentAccount paymentAccount);

    boolean depositMoney(PaymentAccount paymentAccount, BigDecimal amount);

    boolean withdrawMoney(PaymentAccount paymentAccount, BigDecimal amount);
}
