package tech.reliab.course.starodubovLab.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

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

    public CreditAccount(){
        super();
        initWithDefaults();
    }

    public CreditAccount(CreditAccount creditAccount){
        super(creditAccount.id,creditAccount.user,creditAccount.bank);
        this.dateStart = creditAccount.dateStart;
        this.dateEnd = creditAccount.dateEnd;
        this.monthCount = creditAccount.monthCount;
        this.creditAmount  =creditAccount.creditAmount;
        this.remainingCreditAmount = creditAccount.remainingCreditAmount;
        this.monthlyPayment = creditAccount.monthlyPayment;
        this.interestRate = creditAccount.interestRate;
        this.employee = creditAccount.employee;
        this.paymentAccount = creditAccount.paymentAccount;
    }

    public CreditAccount(UUID id, User user, Bank bank, LocalDate dateStart, LocalDate dateEnd, int monthCount, BigDecimal creditAmount, BigDecimal remainingCreditAmount,
                         BigDecimal monthlyPayment, BigDecimal interestRate,Employee employee, PaymentAccount paymentAccount){
        super(id,user,bank);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.monthCount = monthCount;
        this.creditAmount  = creditAmount;
        this.remainingCreditAmount = remainingCreditAmount;
        this.monthlyPayment = monthlyPayment;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    public CreditAccount(User user, Bank bank, LocalDate dateStart, LocalDate dateEnd, int monthCount, BigDecimal creditAmount, BigDecimal remainingCreditAmount,
                         BigDecimal monthlyPayment, BigDecimal interestRate,Employee employee, PaymentAccount paymentAccount){
        super(user,bank);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.monthCount = monthCount;
        this.creditAmount  = creditAmount;
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

    public LocalDate getDateStart(){
        return this.dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd(){
        return this.dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getMonthCount(){
        return this.monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    public BigDecimal getCreditAmount(){
        return this.creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getRemainingCreditAmount(){
        return this.remainingCreditAmount;
    }

    public void setRemainingCreditAmount(BigDecimal remainingCreditAmount) {
        this.remainingCreditAmount = remainingCreditAmount;
    }

    public BigDecimal getMonthlyPayment(){
        return this.monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment){
        this.monthlyPayment = monthlyPayment;
    }

    public BigDecimal getInterestRate(){
        return this.interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Employee getEmployee(){
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PaymentAccount getPaymentAccount(){
        return this.paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    private void initWithDefaults(){
        this.dateStart = null;
        this.dateEnd = null;
        this.monthCount = 0;
        this.creditAmount = new BigDecimal("0");
        this.remainingCreditAmount = new BigDecimal("0");
        this.monthlyPayment = new BigDecimal("0");
        this.interestRate = new BigDecimal("0");
        this.employee = null;
        this.paymentAccount = null;
    }
}
