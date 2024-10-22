package com.JavaLab.online_bank.repository;

import com.JavaLab.online_bank.entity.PaymentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentAccountRepository extends JpaRepository<PaymentAccount,Long> {
    List<PaymentAccount> findAllPaymentAccountsByUserId (Long id);
}
