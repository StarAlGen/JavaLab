package tech.reliab.course.starodubovLab.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class PaymentAccount extends Account {
    private BigDecimal balance;

    public PaymentAccount() {
        super();
        initWithDefaults();
    }

    public PaymentAccount(PaymentAccount paymentAccount) {
        super(paymentAccount.id, paymentAccount.user, paymentAccount.bank);
        this.balance = paymentAccount.balance;
    }

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

    private void initWithDefaults() {
        balance = new BigDecimal("0");
    }
}