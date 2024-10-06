package tech.reliab.course.starodubovLab.bank.service.impl;

import tech.reliab.course.starodubovLab.bank.entity.PaymentAccount;
import tech.reliab.course.starodubovLab.bank.service.PaymentAccountService;

import java.math.BigDecimal;

public class PaymentAccountServiceImpl implements PaymentAccountService {

    @Override
    public PaymentAccount create(PaymentAccount paymentAccount){
        if (paymentAccount == null){
            return null;
        }
        if (paymentAccount.getUser() == null){
            System.err.println("Error: PaymentAccount -  no user");
            return null;
        }
        if (paymentAccount.getBank() == null){
            System.err.println("Error: PaymentAccount - no bank");
            return null;
        }
        if (paymentAccount.getBalance().signum() < 0){
            System.err.println("Error: PaymentAccount - balance must be non-negative");
            return null;
        }
        return new PaymentAccount(paymentAccount);
    }

    @Override
    public boolean depositMoney(PaymentAccount paymentAccount, BigDecimal amount){
        if (paymentAccount == null){
            System.err.println("Error: PaymentAccount - non existing payment account");
            return false;
        }
        if (amount.signum() <= 0){
            System.err.println("Error: PaymentAccount - amount must be positive");
            return false;
        }
        paymentAccount.setBalance(paymentAccount.getBalance().add(amount));
        return true;
    }

    @Override
    public boolean withdrawMoney(PaymentAccount paymentAccount, BigDecimal amount){
        if (paymentAccount == null){
            System.err.println("Error: PaymentAccount - non existing payment account");
            return false;
        }
        if (amount.signum() <= 0){
            System.err.println("Error: PaymentAccount - amount must be positive");
            return false;
        }
        if (paymentAccount.getBalance().compareTo(amount) < 0){
            System.err.println("Error: PaymentAccount - insufficient funds");
            return false;
        }
        paymentAccount.setBalance(paymentAccount.getBalance().subtract(amount));
        return true;
    }
}
