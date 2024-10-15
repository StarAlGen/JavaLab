package tech.reliab.course.starodubovLab.bank.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class Bank {
    private static int currentId;
    public static final BigDecimal MAX_RATING = new BigDecimal("100");
    public static final BigDecimal MAX_TOTAL_MONEY = new BigDecimal("1000000");
    public static final BigDecimal MAX_INTEREST_RATE = new BigDecimal("20");
    private int id;
    private String name;
    private int officeCount;
    private int atmCount;
    private int employeeCount;
    private int userCount;
    private byte rating;
    private BigDecimal totalMoney;
    private BigDecimal interestRate;

    public Bank() {
        initId();
        initWithDefaults();
    }

    private void initId() {
        id = currentId++;
    }

    public Bank(Bank bank) {
        this.id = bank.id;
        this.name = bank.name;
        this.officeCount = bank.officeCount;
        this.atmCount = bank.atmCount;
        this.employeeCount = bank.employeeCount;
        this.userCount = bank.userCount;
        this.rating = bank.rating;
        this.totalMoney = bank.totalMoney;
        this.interestRate = bank.interestRate;
    }

    public Bank(String name) {
        initId();
        initWithDefaults();
        this.name = name;
    }

    public Bank(int id, String name) {
        initWithDefaults();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bank:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n officeCount='" + getOfficeCount() + "'" +
                ",\n atmCount='" + getAtmCount() + "'" +
                ",\n employeeCount='" + getEmployeeCount() + "'" +
                ",\n clientCount='" + getUserCount() + "'" +
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