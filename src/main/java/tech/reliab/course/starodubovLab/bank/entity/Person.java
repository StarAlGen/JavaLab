package tech.reliab.course.starodubovLab.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@AllArgsConstructor
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