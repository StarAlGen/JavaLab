package com.JavaLab.online_bank.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class BankAtm {
    public enum Status {
        NOT_WORKING,
        WORKING,
        NO_MONEY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private String address;
    private Status status;
    @ManyToOne
    private Bank bank;
    @ManyToOne
    private BankOffice bankOffice;
    @ManyToOne
    private Employee employee;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private BigDecimal totalMoney;
    private BigDecimal maintenanceCost;

    public BankAtm(String name, String address) {
        initWithDefaults();
        this.name = name;
        this.address = address;
    }

    public BankAtm(String name, String address, Status status, Bank bank, BankOffice bankOffice,
                   Employee employee, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable, BigDecimal totalMoney,
                   BigDecimal maintenanceCost) {
        initWithDefaults();
        this.name = name;
        this.address = address;
        this.status = status;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.employee = employee;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.maintenanceCost = maintenanceCost;
    }

    @Override
    public String toString() {
        return "BankAtm:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n address='" + getAddress() + "'" +
                ",\n status='" + getStatus() + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                ",\n bankOffice='" + getBankOffice() + "'" +
                ",\n employee='" + getEmployee() + "'" +
                ",\n isCashWithdrawalAvailable='" + isCashWithdrawalAvailable() + "'" +
                ",\n isCashDepositAvailable='" + isCashDepositAvailable() + "'" +
                ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n maintenanceCost='" + String.format("%.2f", getMaintenanceCost()) + "'" +
                "\n}";
    }

    private void initWithDefaults() {
        name = "No name";
        address = "No address";
        status = Status.NOT_WORKING;
        bank = null;
        bankOffice = null;
        employee = null;
        isCashWithdrawalAvailable = false;
        isCashDepositAvailable = false;
        totalMoney = new BigDecimal("0");
        maintenanceCost = new BigDecimal("0");
    }
}
