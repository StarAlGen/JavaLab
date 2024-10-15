package tech.reliab.course.starodubovLab.bank.service.impl;

import tech.reliab.course.starodubovLab.bank.entity.CreditAccount;
import tech.reliab.course.starodubovLab.bank.entity.PaymentAccount;
import tech.reliab.course.starodubovLab.bank.entity.User;
import tech.reliab.course.starodubovLab.bank.service.BankService;
import tech.reliab.course.starodubovLab.bank.service.UserService;
import tech.reliab.course.starodubovLab.bank.utils.BigRandom;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private final Map<Integer, User> usersTable = new HashMap<>();
    private final Map<Integer, List<PaymentAccount>> paymentAccountsByClientIdTable = new HashMap<>();
    private final Map<Integer, List<CreditAccount>> creditAccountsByClientIdTable = new HashMap<>();
    private final BankService bankService;

    public UserServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public User create(User user) {
        if (user == null) {
            return null;
        }

        if (user.getBank() == null) {
            System.err.println("Error: Client - must have bank");
            return null;
        }

        User createdClient = new User(user);

        final BigDecimal monthlyIncome = BigRandom.between(new BigDecimal("0.0"), new BigDecimal("1.0"))
                .multiply(User.MAX_MONTHLY_INCOME);
        createdClient.setMonthlyIncome(monthlyIncome);
        calculateCreditRating(createdClient);

        usersTable.put(createdClient.getId(), createdClient);
        paymentAccountsByClientIdTable.put(createdClient.getId(), new ArrayList<>());
        creditAccountsByClientIdTable.put(createdClient.getId(), new ArrayList<>());
        bankService.addClient(user.getBank().getId(), createdClient);

        return createdClient;
    }

    @Override
    public BigDecimal calculateCreditRating(User user) {
        user.setCreditRating(
                user.getMonthlyIncome().divide(new BigDecimal("1000").multiply(new BigDecimal("100"))));
        return user.getCreditRating();
    }

    @Override
    public boolean addCreditAccount(int id, CreditAccount account) {
        User user = usersTable.get(id);
        if (user != null) {
            List<CreditAccount> userCreditAccounts = creditAccountsByClientIdTable.get(id);
            userCreditAccounts.add(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean addPaymentAccount(int id, PaymentAccount account) {
        User client = usersTable.get(id);
        if (client != null) {
            List<PaymentAccount> userCreditAccounts = paymentAccountsByClientIdTable.get(id);
            userCreditAccounts.add(account);
            return true;
        }
        return false;
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccountsByUserId(int id) {
        return paymentAccountsByClientIdTable.get(id);
    }

    @Override
    public List<CreditAccount> getAllCreditAccountsByUserId(int id) {
        return creditAccountsByClientIdTable.get(id);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(usersTable.values());
    }

    @Override
    public User getClientById(int id) {
        User user = usersTable.get(id);
        if (user == null) {
            System.err.println("Client with id " + id + " is not found");
        }
        return user;
    }

    @Override
    public void printClientData(int id, boolean withAccounts) {
        User user = getClientById(id);

        if (user == null) {
            return;
        }

        System.out.println(user);
        if (withAccounts) {
            List<PaymentAccount> paymentAccounts = getAllPaymentAccountsByUserId(id);
            if (paymentAccounts != null) {
                System.out.println("Payment accounts:");
                paymentAccounts.forEach(System.out::println);
            }
            List<CreditAccount> creditAccounts = getAllCreditAccountsByUserId(id);
            if (creditAccounts != null) {
                System.out.println("Credit accounts:");
                creditAccounts.forEach(System.out::println);
            }
        }
    }
}
