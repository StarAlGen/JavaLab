package com.JavaLab.online_bank.repository;

import com.JavaLab.online_bank.entity.BankAtm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAtmRepository extends JpaRepository<BankAtm,Long> {
    List<BankAtm> findAllBankAtmsByBankOfficeId(Long id);
}
