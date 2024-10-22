package com.JavaLab.online_bank.repository;

import com.JavaLab.online_bank.entity.BankOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankOfficeRepository extends JpaRepository<BankOffice,Long> {
    List<BankOffice> findAllBankOfficesByBankId(Long id);
}
