package tech.reliab.course.starodubovLab.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Account {
    private static int currentId;
    protected int id;
    protected User user;
    protected Bank bank;

    private void initId() {
        id = currentId++;
    }

    public Account() {
        initId();
        initWithDefaults();
    }

    public Account(User user, Bank bank) {
        initId();
        initWithDefaults();
        this.user = user;
        this.bank = bank;
    }

    public Account(Account account) {
        this.id = account.id;
        this.user = account.user;
        this.bank = account.bank;
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
