package com.JavaLab.online_bank.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class BankOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private String address;
    @ManyToOne
    private Bank bank;
    private boolean isWorking;
    private boolean isAtmPlaceable;
    private int atmCount;
    private boolean isCreditAvailable;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private BigDecimal totalMoney;
    private BigDecimal rentPrice;

    public BankOffice(String name, String address) {
        initWithDefaults();
        this.name = name;
        this.address = address;
    }

    public BankOffice(String name, String address, Bank bank, boolean isWorking, boolean isAtmPlaceable,
                      int atmCount, boolean isCreditAvailable, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable,
                      BigDecimal totalMoney, BigDecimal rentPrice) {
        initWithDefaults();
        this.name = name;
        this.address = address;
        this.bank = bank;
        this.isWorking = isWorking;
        this.isAtmPlaceable = isAtmPlaceable;
        this.atmCount = atmCount;
        this.isCreditAvailable = isCreditAvailable;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.rentPrice = rentPrice;
    }

    @Override
    public String toString() {
        return "BankOffice:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n address='" + getAddress() + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                ",\n isWorking='" + isWorking() + "'" +
                ",\n isAtmPlaceable='" + isAtmPlaceable() + "'" +
                ",\n atmCount='" + getAtmCount() + "'" +
                ",\n isCreditAvailable='" + isCreditAvailable() + "'" +
                ",\n isCashWithdrawalAvailable='" + isCashWithdrawalAvailable() + "'" +
                ",\n isCashDepositAvailable='" + isCashDepositAvailable() + "'" +
                ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n rentPrice='" + String.format("%.2f", getRentPrice()) + "'" +
                "\n}";
    }

    private void initWithDefaults() {
        name = "No name";
        address = "No address";
        bank = null;
        isWorking = false;
        isAtmPlaceable = false;
        atmCount = 0;
        isCreditAvailable = false;
        isCashWithdrawalAvailable = false;
        isCashDepositAvailable = false;
        totalMoney = new BigDecimal("0");
        rentPrice = new BigDecimal("0");
    }
}
