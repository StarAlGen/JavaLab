package com.JavaLab.online_bank.service.impl;

import com.JavaLab.online_bank.entity.CreditAccount;
import com.JavaLab.online_bank.entity.PaymentAccount;
import com.JavaLab.online_bank.entity.User;
import com.JavaLab.online_bank.repository.CreditAccountRepository;
import com.JavaLab.online_bank.repository.PaymentAccountRepository;
import com.JavaLab.online_bank.repository.UserRepository;
import com.JavaLab.online_bank.service.UserService;
import com.JavaLab.online_bank.utils.BigRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CreditAccountRepository creditAccountRepository;
    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Override
    public User create(User user) {
        if (user == null) {
            return null;
        }

        if (user.getBank() == null) {
            System.err.println("Error: User - must have bank");
            return null;
        }

        final BigDecimal monthlyIncome = BigRandom.between(new BigDecimal("0.0"), new BigDecimal("1.0"))
                .multiply(User.MAX_MONTHLY_INCOME);
        user.setMonthlyIncome(monthlyIncome);
        calculateCreditRating(user);

        return userRepository.save(user);
    }

    @Override
    public void calculateCreditRating(User user) {
        user.setCreditRating(
                user.getMonthlyIncome().divide(new BigDecimal("1000").multiply(new BigDecimal("100"))));
        userRepository.save(user);
    }

    @Override
    public boolean addCreditAccount(Long id, CreditAccount account) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            account.setUser(user);
            creditAccountRepository.save(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean addPaymentAccount(Long id, PaymentAccount account) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            account.setUser(user);
            paymentAccountRepository.save(account);
            return true;
        }
        return false;
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccountsByUserId(Long id) {
        return paymentAccountRepository.findAllPaymentAccountsByUserId(id);
    }

    @Override
    public List<CreditAccount> getAllCreditAccountsByUserId(Long id) {
        return creditAccountRepository.findAllCreditAccountsByUserId(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            System.err.println("User with id " + id + " is not found");
        }
        return user;
    }

    @Override
    public void printUserData(Long id, boolean withAccounts) {
        User user = getUserById(id);

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
