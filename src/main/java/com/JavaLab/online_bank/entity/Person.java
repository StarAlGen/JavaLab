package com.JavaLab.online_bank.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    protected Long id;
    protected String name;
    protected LocalDate birthDate;

    public Person(String name, LocalDate birthDate) {
        initWithDefaults();
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n birthDate='" + getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "'" +
                "\n}";
    }

    private void initWithDefaults() {
        name = "No name";
        birthDate = null;
    }
}
