package com.JavaLab.online_bank.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    protected Long id;
    @ManyToOne
    protected User user;
    @ManyToOne
    protected Bank bank;

    public Account(User user, Bank bank) {
        initWithDefaults();
        this.user = user;
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "Account:{" +
                "\n id='" + getId() + "'" +
                ",\n user='" + getUser() + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                "\n}";
    }

    private void initWithDefaults() {
        user = null;
        bank = null;
    }
}
