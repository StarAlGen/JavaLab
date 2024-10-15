package tech.reliab.course.starodubovLab.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class CreditAccount extends Account {
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int monthCount;
    private BigDecimal creditAmount;
    private BigDecimal remainingCreditAmount;
    private BigDecimal monthlyPayment;
    private BigDecimal interestRate;
    private Employee employee;
    private PaymentAccount paymentAccount;

    public CreditAccount() {
        super();
        initWithDefaults();
    }

    public CreditAccount(CreditAccount creditAccount) {
        super(creditAccount.id, creditAccount.user, creditAccount.bank);
        this.dateStart = creditAccount.dateStart;
        this.dateEnd = creditAccount.dateEnd;
        this.monthCount = creditAccount.monthCount;
        this.creditAmount = creditAccount.creditAmount;
        this.remainingCreditAmount = creditAccount.remainingCreditAmount;
        this.monthlyPayment = creditAccount.monthlyPayment;
        this.interestRate = creditAccount.interestRate;
        this.employee = new Employee(creditAccount.employee);
        this.paymentAccount = new PaymentAccount(creditAccount.paymentAccount);
    }

    public CreditAccount(User user, Bank bank, LocalDate dateStart, LocalDate dateEnd, int monthCount,
                         BigDecimal creditAmount, BigDecimal remainingCreditAmount, BigDecimal monthlyPayment,
                         BigDecimal interestRate, Employee employee, PaymentAccount paymentAccount) {
        super(user, bank);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.monthCount = monthCount;
        this.creditAmount = creditAmount;
        this.remainingCreditAmount = remainingCreditAmount;
        this.monthlyPayment = monthlyPayment;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    @Override
    public String toString() {
        return "CreditAccount:{" +
                "\n account='" + super.toString() + "'" +
                ",\n dateStart='" + getDateStart() + "'" +
                ",\n dateEnd='" + getDateEnd() + "'" +
                ",\n monthCount='" + getMonthCount() + "'" +
                ",\n creditAmount='" + String.format("%.2f", getCreditAmount()) + "'" +
                ",\n remainingCreditAmount='" + String.format("%.2f", getRemainingCreditAmount()) + "'" +
                ",\n montlyPayment='" + String.format("%.2f", getMonthlyPayment()) + "'" +
                ",\n interestRate='" + String.format("%.2f", getInterestRate()) + "'" +
                ",\n employee='" + getEmployee() + "'" +
                ",\n paymentAccount='" + getPaymentAccount() + "'" +
                "\n}";
    }

    private void initWithDefaults() {
        dateStart = null;
        dateEnd = null;
        monthCount = 0;
        creditAmount = new BigDecimal("0");
        remainingCreditAmount = new BigDecimal("0");
        monthlyPayment = new BigDecimal("0");
        interestRate = new BigDecimal("0");
        employee = null;
        paymentAccount = null;
    }
}
