package tech.reliab.course.starodubovLab.bank.service.impl;

import tech.reliab.course.starodubovLab.bank.entity.PaymentAccount;
import tech.reliab.course.starodubovLab.bank.service.PaymentAccountService;
import tech.reliab.course.starodubovLab.bank.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentAccountServiceImpl implements PaymentAccountService {

    private final Map<Integer, PaymentAccount> paymentAccountsTable = new HashMap<>();
    private final UserService userService;

    public PaymentAccountServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public PaymentAccount create(PaymentAccount paymentAccount) {
        if (paymentAccount == null) {
            return null;
        }

        if (paymentAccount.getBalance().signum() < 0) {
            System.err.println("Error: PaymentAccount - payment account balance must be non-negative");
            return null;
        }

        PaymentAccount newAccount = new PaymentAccount(paymentAccount);
        paymentAccountsTable.put(newAccount.getId(), newAccount);
        userService.addPaymentAccount(newAccount.getUser().getId(), newAccount);

        return newAccount;
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

        return true;
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccounts() {
        return new ArrayList<PaymentAccount>(paymentAccountsTable.values());
    }

    @Override
    public PaymentAccount getPaymentAccountById(int id) {
        PaymentAccount account = paymentAccountsTable.get(id);
        if (account == null) {
            System.err.println("Payment account with id " + id + " is not found");
        }
        return account;
    }

    @Override
    public void printPaymentData(int id) {
        PaymentAccount account = getPaymentAccountById(id);
        if (account == null) {
            return;
        }

        System.out.println(account);
    }
}
