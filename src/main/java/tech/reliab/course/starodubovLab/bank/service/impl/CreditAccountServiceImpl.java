package tech.reliab.course.starodubovLab.bank.service.impl;

import tech.reliab.course.starodubovLab.bank.entity.CreditAccount;
import tech.reliab.course.starodubovLab.bank.service.CreditAccountService;

import java.math.BigDecimal;

public class CreditAccountServiceImpl implements CreditAccountService {
    public CreditAccount create(CreditAccount creditAccount){
        if (creditAccount == null){
            return null;
        }
        if (creditAccount.getBank() == null){
            System.err.println("Error: CreditAccount - there must be a bank in which this account is registered ");
            return null;
        }
        if (creditAccount.getUser() == null){
            System.err.println("Error: CreditAccount - does not have a user");
            return null;
        }
        if (creditAccount.getMonthCount() < 1){
            System.err.println("Error: CreditAccount - the number of months must be at least 1");
            return null;
        }
        if (creditAccount.getCreditAmount().signum() <= 0){
            System.err.println("Error: CreditAccount - credit amount must be positive");
            return null;
        }
        return new CreditAccount(creditAccount);
    }

    public boolean makeMonthlyPayment(CreditAccount creditAccount){
        if (creditAccount == null || creditAccount.getPaymentAccount() == null){
            System.err.println("Error: CreditAccount - no account to take money from");
            return false;
        }
        final BigDecimal monthlyPayment = creditAccount.getMonthlyPayment();
        final BigDecimal paymentAccountBalance = creditAccount.getPaymentAccount().getBalance();
        if (paymentAccountBalance.compareTo(monthlyPayment) < 0){
            System.err.println("Error: CreditAccount - insufficient funds for monthly payment");
            return false;
        }
        creditAccount.setRemainingCreditAmount(creditAccount.getRemainingCreditAmount().subtract(monthlyPayment));
        creditAccount.getPaymentAccount().setBalance(paymentAccountBalance.subtract(monthlyPayment));
        return true;
    }
}
