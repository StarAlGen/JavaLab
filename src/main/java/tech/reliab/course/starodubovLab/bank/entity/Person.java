package tech.reliab.course.starodubovLab.bank.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {
    private static int currentId;
    protected int id;
    protected String name;
    protected LocalDate birthDate;

    private void initId() {
        id = currentId++;
    }

    public Person() {
        initId();
        initWithDefaults();
    }

    public Person(Person person) {
        this.id = person.id;
        this.name = person.name;
        this.birthDate = person.birthDate;
    }

    public Person(String name, LocalDate birthDate) {
        initId();
        initWithDefaults();
        this.name = name;
        this.birthDate = birthDate;
    }

    public Person(int id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n birthdDate='" + getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "'" +
                "\n}";
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    private void initWithDefaults() {
        name = "No name";
        birthDate = null;
    }
}