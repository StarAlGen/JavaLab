package com.JavaLab.online_bank.service;

import com.JavaLab.online_bank.entity.CreditAccount;
import com.JavaLab.online_bank.entity.PaymentAccount;
import com.JavaLab.online_bank.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    User create(User user);

    public void printUserData(Long id, boolean withAccounts);

    public User getUserById(Long id);

    public List<User> getAllUsers();

    public boolean addPaymentAccount(Long id, PaymentAccount account);

    public boolean addCreditAccount(Long id, CreditAccount account);

    public List<PaymentAccount> getAllPaymentAccountsByUserId(Long id);

    public List<CreditAccount> getAllCreditAccountsByUserId(Long id);

    void calculateCreditRating(User user);
}
