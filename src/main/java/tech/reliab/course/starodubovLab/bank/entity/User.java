package tech.reliab.course.starodubovLab.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class User extends Person {
    public static final BigDecimal MAX_MONTHLY_INCOME = new BigDecimal("10000");
    private String placeOfWork;
    private BigDecimal monthlyIncome;
    private Bank bank;
    private BigDecimal creditRating;

    public User() {
        initWithDefaults();
    }

    public User(User user) {
        super(user.id, user.name, user.birthDate);
        this.placeOfWork = user.placeOfWork;
        this.monthlyIncome = user.monthlyIncome;
        this.bank = new Bank(user.bank);
        this.creditRating = user.creditRating;
    }

    public User(String name, LocalDate birthDate, String placeOfWork, BigDecimal monthlyIncome, Bank bank,
                  BigDecimal creditRating) {
        super(name, birthDate);
        initWithDefaults();
        this.name = name;
        this.birthDate = birthDate;
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    @Override
    public String toString() {
        return "Client:{" +
                "\n person='" + super.toString() + "'" +
                ",\n placeOfWork='" + getPlaceOfWork() + "'" +
                ",\n monthlyIncome='" + String.format("%.2f", getMonthlyIncome()) + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                ",\n creditRating='" + String.format("%.2f", getCreditRating()) + "'" +
                "\n}";
    }

    private void initWithDefaults() {
        placeOfWork = "No place of work";
        monthlyIncome = new BigDecimal("0");
        bank = null;
        creditRating = new BigDecimal("0");
    }
}