package tech.reliab.course.starodubovLab.bank.entity;

import java.time.LocalDate;
import java.util.UUID;

public class Person {
    protected UUID id;
    protected String fullName;
    protected LocalDate birthDate;

    public Person(){
        initWithDefaults();
    }

    public Person(UUID id, String fullName, LocalDate birthDate){
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public Person(String fullName, LocalDate birthDate){
        initWithDefaults();
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public Person(Person person){
        this.id = person.id;
        this.fullName = person.fullName;
        this.birthDate = person.birthDate;
    }

    @Override
    public String toString() {
        return "Person:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getFullName() + "'" +
                ",\n birthdDate='" + getBirthDate() + "'" +
                "\n}";
    }

    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName(){
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate(){
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    private void initWithDefaults(){
        id = UUID.randomUUID();
        fullName = "none";
        birthDate = null;
    }
}
