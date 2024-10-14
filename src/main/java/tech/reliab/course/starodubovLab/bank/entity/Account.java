package tech.reliab.course.starodubovLab.bank.entity;

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

    public Account(int id, User user, Bank bank) {
        this.id = id;
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
                ",\n client='" + getClient() + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                "\n}";
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getClient() {
        return this.user;
    }

    public void setClient(User user) {
        this.user = user;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    private void initWithDefaults() {
        user = null;
        bank = null;
    }
}
