package com.JavaLab.online_bank.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class PaymentAccount extends Account {
    private BigDecimal balance;

    public PaymentAccount(User user, Bank bank, BigDecimal balance) {
        super(user, bank);
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "PaymentAccount:{" +
                "\n account='" + super.toString() + "'" +
                ",\n balance='" + String.format("%.2f", getBalance()) + "'" +
                "\n}";
    }

}
