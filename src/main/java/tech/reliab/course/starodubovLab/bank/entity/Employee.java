package tech.reliab.course.starodubovLab.bank.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class Employee extends Person {

    public enum Job {
        CEO,
        VaultKeeper,
        Programmer,
        Lawyer,
        Cashier,
        Manager;

        public static Job getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    private Job job;
    private Bank bank;
    private boolean isWorkingFromHome;
    private BankOffice bankOffice;
    private boolean isCreditAvailable;
    private BigDecimal salary;

    public Employee() {
        super();
        initWithDefaults();
    }

    public Employee(Employee employee) {
        super(employee.id, employee.name, employee.birthDate);
        this.job = employee.job;
        this.bank = new Bank(employee.bank);
        this.isWorkingFromHome = employee.isWorkingFromHome;
        this.bankOffice = new BankOffice(employee.bankOffice);
        this.isCreditAvailable = employee.isCreditAvailable;
        this.salary = employee.salary;
    }

    public Employee(String name, LocalDate birthDate, Job job, Bank bank, boolean isWorkingFromHome,
                    BankOffice bankOffice, boolean isCreditAvailable, BigDecimal salary) {
        super(name, birthDate);
        this.job = job;
        this.bank = bank;
        this.isWorkingFromHome = isWorkingFromHome;
        this.bankOffice = bankOffice;
        this.isCreditAvailable = isCreditAvailable;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee:{" +
                "\n person='" + super.toString() + "'" +
                ",\n job='" + getJob() + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                ",\n isWorkingFromHome='" + isWorkingFromHome() + "'" +
                ",\n bankOffice='" + getBankOffice() + "'" +
                ",\n isCreditAvailable='" + isCreditAvailable() + "'" +
                ",\n salary='" + String.format("%.2f", getSalary()) + "'" +
                "\n}";
    }

    private void initWithDefaults() {
        job = null;
        bank = null;
        isWorkingFromHome = false;
        bankOffice = null;
        isCreditAvailable = false;
        salary = new BigDecimal("0");
    }
}