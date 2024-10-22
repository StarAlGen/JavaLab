package com.JavaLab.online_bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Employee extends Person {

    public enum Job {
        CEO,
        VaultKeeper,
        Programmer,
        Lawyer,
        Cashier,
        Manager;

        public static Job getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    private Job job;
    @ManyToOne
    private Bank bank;
    private boolean isWorkingFromHome;
    @ManyToOne
    private BankOffice bankOffice;
    private boolean isCreditAvailable;
    private BigDecimal salary;

    public Employee(String name, LocalDate birthDate, Job job, Bank bank, boolean isWorkingFromHome,
                    BankOffice bankOffice, boolean isCreditAvailable, BigDecimal salary) {
        super(name, birthDate);
        this.job = job;
        this.bank = bank;
        this.isWorkingFromHome = isWorkingFromHome;
        this.bankOffice = bankOffice;
        this.isCreditAvailable = isCreditAvailable;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee:{" +
                "\n person='" + super.toString() + "'" +
                ",\n job='" + getJob() + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                ",\n isWorkingFromHome='" + isWorkingFromHome() + "'" +
                ",\n bankOffice='" + getBankOffice() + "'" +
                ",\n isCreditAvailable='" + isCreditAvailable() + "'" +
                ",\n salary='" + String.format("%.2f", getSalary()) + "'" +
                "\n}";
    }

    private void initWithDefaults() {
        job = null;
        bank = null;
        isWorkingFromHome = false;
        bankOffice = null;
        isCreditAvailable = false;
        salary = new BigDecimal("0");
    }
}
