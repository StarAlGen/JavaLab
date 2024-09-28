package tech.reliab.course.starodubovLab.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Employee extends Person {
    public enum Job{
        Manager,
        Worker
    }
    private Job job;
    private Bank bank;
    private boolean isWorkingFromHome;
    private BankOffice bankOffice;
    private boolean isCreditAvailable;
    private BigDecimal salaryAmount;

    public Employee(){
        super();
        initWithDefaults();
    }

    public Employee(UUID id, String fullName, LocalDate birthDate, Job job, Bank bank, boolean isWorkingFromHome, BankOffice bankOffice, boolean isCreditAvailable, BigDecimal salaryAmount){
        super(id,fullName,birthDate);
        this.job = job;
        this.bank = bank;
        this.isWorkingFromHome = isWorkingFromHome;
        this.bankOffice = bankOffice;
        this.isCreditAvailable = isCreditAvailable;
        this.salaryAmount = salaryAmount;
    }

    public Employee(String fullName, LocalDate birthDate, Job job, Bank bank, boolean isWorkingFromHome, BankOffice bankOffice, boolean isCreditAvailable, BigDecimal salaryAmount){
        super(fullName,birthDate);
        this.job = job;
        this.bank = bank;
        this.isWorkingFromHome = isWorkingFromHome;
        this.bankOffice = bankOffice;
        this.isCreditAvailable = isCreditAvailable;
        this.salaryAmount = salaryAmount;
    }

    public Employee (Employee employee){
        super(employee.id,employee.fullName,employee.birthDate);
        this.job = employee.job;
        this.bank = employee.bank;
        this.isWorkingFromHome = employee.isWorkingFromHome;
        this.bankOffice = employee.bankOffice;
        this.isCreditAvailable = employee.isCreditAvailable;
        this.salaryAmount = employee.salaryAmount;
    }

    @Override
    public String toString() {
        return "Employee:{" +
                "\n person='" + super.toString() + "'" +
                ",\n job='" + getJob() + "'" +
                ",\n bank='" + getBank() + "'" +
                ",\n isWorkingFromHome='" + isWorkingFromHome() + "'" +
                ",\n bankOffice='" + getBankOffice() + "'" +
                ",\n isCreditAvailable='" + isCreditAvailable() + "'" +
                ",\n salary='" + String.format("%.2f", getSalaryAmount()) + "'" +
                "\n}";
    }

    public Job getJob(){
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Bank getBank(){
        return this.bank;
    }

    public void setBank(Bank bank){
        this.bank  = bank;
    }

    public boolean isWorkingFromHome(){
        return this.isWorkingFromHome;
    }

    public void setWorkingFromHome(boolean workingFromHome) {
        isWorkingFromHome = workingFromHome;
    }

    public BankOffice getBankOffice(){
        return this.bankOffice;
    }

    public void setBankOffice(BankOffice bankOffice) {
        this.bankOffice = bankOffice;
    }

    public boolean isCreditAvailable(){
        return this.isCreditAvailable;
    }

    public void setCreditAvailable(boolean creditAvailable) {
        isCreditAvailable = creditAvailable;
    }

    public BigDecimal getSalaryAmount(){
        return this.salaryAmount;
    }

    public void setSalaryAmount(BigDecimal salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    private void initWithDefaults(){
        job = null;
        bank = null;
        isWorkingFromHome = false;
        bankOffice =  null;
        isCreditAvailable = false;
        salaryAmount = new BigDecimal("0");
    }
}