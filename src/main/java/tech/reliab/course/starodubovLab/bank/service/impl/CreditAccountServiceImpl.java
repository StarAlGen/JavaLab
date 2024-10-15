package tech.reliab.course.starodubovLab.bank.service.impl;

import tech.reliab.course.starodubovLab.bank.entity.CreditAccount;
import tech.reliab.course.starodubovLab.bank.service.CreditAccountService;
import tech.reliab.course.starodubovLab.bank.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditAccountServiceImpl implements CreditAccountService {

    private final Map<Integer, CreditAccount> creditAccountsTable = new HashMap<>();
    private final UserService userService;

    public CreditAccountServiceImpl(UserService userService) {
        this.userService = userService;
    }

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

        CreditAccount newAccount = new CreditAccount(creditAccount);
        creditAccountsTable.put(newAccount.getId(), newAccount);
        userService.addCreditAccount(newAccount.getUser().getId(), newAccount);

        return newAccount;
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

        return true;
    }

    @Override
    public List<CreditAccount> getAllCreditAccounts() {
        return new ArrayList<CreditAccount>(creditAccountsTable.values());
    }

    @Override
    public CreditAccount getCreditAccountById(int id) {
        CreditAccount account = creditAccountsTable.get(id);
        if (account == null) {
            System.err.println("Credit account with id " + id + " is not found");
        }
        return account;
    }
}
