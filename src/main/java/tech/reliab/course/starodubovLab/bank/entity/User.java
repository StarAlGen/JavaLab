package tech.reliab.course.starodubovLab.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class User extends Person {
    public static final BigDecimal MAX_MONTHLY_INCOME = new BigDecimal("10000");
    private String placeOfWork;
    private BigDecimal monthlyIncome;
    private Bank bank;
    private BigDecimal creditRating;

    public User(){
        super();
        initWithDefaults();
    }

    public User (UUID id, String fullName, LocalDate birthDate, String placeOfWork, BigDecimal monthlyIncome, Bank bank, BigDecimal creditRating){
        super(id,fullName,birthDate);
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    public User (String fullName, LocalDate birthDate, String placeOfWork, BigDecimal monthlyIncome, Bank bank, BigDecimal creditRating){
        super(fullName,birthDate);
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    public User (User user){
        super(user.id,user.fullName,user.birthDate);
        this.placeOfWork = user.placeOfWork;
        this.monthlyIncome = user.monthlyIncome;
        this.bank  = user.bank;
        this.creditRating = user.creditRating;
    }

    @Override
    public String toString(){
        return "Clien:{" +
                "\n person='" + super.toString() + "'" +
                ",\n placeOfWork='" + getPlaceOfWork() + "'" +
                ",\n monthlyIncome='" + String.format("%.2f", getMonthlyIncome()) + "'" +
                ",\n bank='" + getBank() + "'" +
                ",\n creditRating='" + String.format("%.2f", getCreditRating()) + "'" +
                "\n}";
    }

    public String getPlaceOfWork(){
        return this.placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public BigDecimal getMonthlyIncome(){
        return this.monthlyIncome;
    }

    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Bank getBank(){
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
        if (bank != null){
            bank.setUserCount(bank.getUserCount() + 1);
        }
    }

    public BigDecimal getCreditRating(){
        return this.creditRating;
    }

    public void setCreditRating(BigDecimal creditRating) {
        this.creditRating = creditRating;
    }

    private void initWithDefaults(){
        placeOfWork = "none";
        monthlyIncome = new BigDecimal(String.valueOf(Math.random()*MAX_MONTHLY_INCOME.doubleValue()));
        bank = null;
        creditRating = new BigDecimal(String.valueOf((monthlyIncome.intValue() / 1000 + 1) * 100));
    }
}