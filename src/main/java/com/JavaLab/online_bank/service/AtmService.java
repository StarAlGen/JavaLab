package com.JavaLab.online_bank.service;

import com.JavaLab.online_bank.entity.BankAtm;

import java.math.BigDecimal;
import java.util.List;

public interface AtmService {
    public BankAtm create(BankAtm bankAtm);

    public void delete (Long id);

    public BankAtm getBankAtmById(Long id);

    public List<BankAtm> getAllBankAtms();

    public boolean depositMoney(BankAtm bankAtm, BigDecimal amount);

    public boolean withdrawMoney(BankAtm bankAtm, BigDecimal amount);
}
