package tech.reliab.course.starodubovLab.bank.entity;

import java.util.UUID;

public class Account {
    protected UUID id;
    protected User user;
    protected Bank bank;

    public Account(){
        initWithDefaults();
    }

    public Account(UUID id, User user, Bank bank){
        this.id = id;
        this.user = user;
        this.bank = bank;
    }

    public Account(User user, Bank bank){
        this.id = UUID.randomUUID();
        this.user = user;
        this.bank = bank;
    }

    public Account(Account account){
        this.id = account.id;
        this.user = account.user;
        this.bank = account.bank;
    }

    @Override
    public String toString() {
        return "Account:{" +
                "\n id='" + getId() + "'" +
                ",\n client='" + getUser() + "'" +
                ",\n bank='" + getBank() + "'" +
                "\n}";
    }

    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public User getUser(){
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bank getBank(){
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    private void initWithDefaults(){
        id = UUID.randomUUID();
        user = null;
        bank = null;
    }
}
