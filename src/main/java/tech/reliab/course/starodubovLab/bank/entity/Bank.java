package tech.reliab.course.starodubovLab.bank.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Bank {
    public static final byte MAX_RATING = 100;
    public static final BigDecimal MAX_TOTAL_MONEY = new BigDecimal("1000000");
    public static final BigDecimal MAX_INTEREST_RATE = new BigDecimal("20");
    private UUID id;
    private String name;
    private int officeCount;
    private int atmCount;
    private int employeeCount;
    private int userCount;
    private byte rating;
    private BigDecimal totalMoney;
    private BigDecimal interestRate;

    public Bank(){
        initWithDefaults();
    }

    public Bank(Bank bank){
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

    public Bank(String name){
        initWithDefaults();
        this.name = name;
    }

    public Bank(UUID id, String name){
        initWithDefaults();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString(){
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

    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getOfficeCount(){
        return this.officeCount;
    }

    public void setOfficeCount(int officeCount){
        this.officeCount = officeCount;
    }

    public int getAtmCount(){
        return this.atmCount;
    }

    public void setAtmCount(int atmCount){
        this.atmCount = atmCount;
    }

    public int getEmployeeCount(){
        return this.employeeCount;
    }

    public void setEmployeeCount(int employeeCount){
        this.employeeCount = employeeCount;
    }

    public int getUserCount(){
        return this.userCount;
    }

    public void setUserCount(int userCount){
        this.userCount = userCount;
    }

    public byte getRating(){
        return this.rating;
    }

    public void setRating(byte rating){
        this.rating = rating;
    }

    public BigDecimal getTotalMoney(){
        return this.totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney){
        this.totalMoney = totalMoney;
    }

    public BigDecimal getInterestRate(){
        return this.interestRate;
    }

    public void setInterestRate(BigDecimal interestRate){
        this.interestRate = interestRate;
    }

    private void initWithDefaults(){
        id = UUID.randomUUID();
        name = "None";
        officeCount = 0;
        atmCount = 0;
        employeeCount = 0;
        userCount = 0;
        rating = (byte) (Math.random() * (MAX_RATING + 1));
        interestRate = new BigDecimal(String.valueOf(MAX_INTEREST_RATE.doubleValue() / (this.rating + 1)));
        totalMoney = new BigDecimal(String.valueOf(Math.random() * MAX_TOTAL_MONEY.doubleValue()));
    }
}