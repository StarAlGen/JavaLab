package com.JavaLab.online_bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class Bank {
    public static final BigDecimal MAX_RATING = new BigDecimal("100");
    public static final BigDecimal MAX_TOTAL_MONEY = new BigDecimal("1000000");
    public static final BigDecimal MAX_INTEREST_RATE = new BigDecimal("20");
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private int officeCount;
    private int atmCount;
    private int employeeCount;
    private int userCount;
    private byte rating;
    private BigDecimal totalMoney;
    private BigDecimal interestRate;

    public Bank(String name) {
        initWithDefaults();
        this.name = name;
    }

    public Bank(String name, int officeCount, int atmCount, int employeeCount,
                int userCount, byte rating, BigDecimal totalMoney, BigDecimal interestRate){
        this.name = name;
        this.officeCount = officeCount;
        this.atmCount = atmCount;
        this.employeeCount = employeeCount;
        this.userCount = userCount;
        this.rating = rating;
        this.totalMoney = totalMoney;
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "Bank:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n officeCount='" + getOfficeCount() + "'" +
                ",\n atmCount='" + getAtmCount() + "'" +
                ",\n employeeCount='" + getEmployeeCount() + "'" +
                ",\n userCount='" + getUserCount() + "'" +
                ",\n rating='" + getRating() + "'" +
                ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n interestRate='" + String.format("%.2f", getInterestRate()) + "'" +
                "\n}";
    }

    private void initWithDefaults() {
        name = "No name";
        officeCount = 0;
        atmCount = 0;
        employeeCount = 0;
        userCount = 0;
        rating = 0;
        totalMoney = new BigDecimal("0");
        interestRate = new BigDecimal("0");
    }
}
