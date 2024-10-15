package tech.reliab.course.starodubovLab.bank.service;

import tech.reliab.course.starodubovLab.bank.entity.CreditAccount;
import tech.reliab.course.starodubovLab.bank.entity.PaymentAccount;
import tech.reliab.course.starodubovLab.bank.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    User create(User user);

    public void printClientData(int id, boolean withAccounts);

    public User getClientById(int id);

    public List<User> getAllUsers();

    public boolean addPaymentAccount(int id, PaymentAccount account);

    public boolean addCreditAccount(int id, CreditAccount account);

    public List<PaymentAccount> getAllPaymentAccountsByUserId(int id);

    public List<CreditAccount> getAllCreditAccountsByUserId(int id);

    BigDecimal calculateCreditRating(User user);
}
