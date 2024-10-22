package com.JavaLab.online_bank.repository;

import com.JavaLab.online_bank.entity.CreditAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditAccountRepository extends JpaRepository<CreditAccount,Long> {
    List<CreditAccount> findAllCreditAccountsByUserId (Long id);
}
