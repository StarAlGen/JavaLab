package tech.reliab.course.starodubovLab.bank.service;

import tech.reliab.course.starodubovLab.bank.entity.BankAtm;

import java.math.BigDecimal;
import java.util.List;

public interface AtmService {
    BankAtm create(BankAtm bankAtm);

    public BankAtm getBankAtmById(int id);

    public List<BankAtm> getAllBankAtms();

    boolean depositMoney(BankAtm bankAtm, BigDecimal amount);

    boolean withdrawMoney(BankAtm bankAtm, BigDecimal amount);
}
