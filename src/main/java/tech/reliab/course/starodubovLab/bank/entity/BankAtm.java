package tech.reliab.course.starodubovLab.bank.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class BankAtm {
    public enum Status{
        WORKING,
        NOT_WORKING,
        NO_MONEY
    }
    private UUID id;
    private String name;
    private String address;
    private Status status;
    private Bank bank;
    private BankOffice bankOffice;
    private Employee employee;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private BigDecimal totalMoney;
    private BigDecimal maintenanceCost;

    public BankAtm(){
        initWithDefaults();
    }

    public BankAtm(UUID id, String name, String address, Status status, Bank bank, BankOffice bankOffice, Employee employee, boolean isCashWithdrawalAvailable
    , boolean isCashDepositAvailable, BigDecimal totalMoney, BigDecimal maintenanceCost){
        this.id = id;
        this.name = name;
        this.address = address;
        this.status = status;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.employee = employee;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.maintenanceCost = maintenanceCost;
    }

    public BankAtm(String name, String address, Status status, Bank bank, BankOffice bankOffice, Employee employee, boolean isCashWithdrawalAvailable
            , boolean isCashDepositAvailable, BigDecimal totalMoney, BigDecimal maintenanceCost){
        initWithDefaults();
        this.name = name;
        this.address = address;
        this.status = status;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.employee = employee;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.maintenanceCost = maintenanceCost;
    }

    public BankAtm(BankAtm bankAtm){
        this.id = bankAtm.id;
        this.name = bankAtm.name;
        this.address = bankAtm.address;
        this.status = bankAtm.status;
        this.bank = bankAtm.bank;
        this.bankOffice = bankAtm.bankOffice;
        this.employee = bankAtm.employee;
        this.isCashWithdrawalAvailable = bankAtm.isCashWithdrawalAvailable;
        this.isCashDepositAvailable = bankAtm.isCashDepositAvailable;
        this.totalMoney = bankAtm.totalMoney;
        this.maintenanceCost = bankAtm.maintenanceCost;
    }

    @Override
    public String toString() {
        return "BankAtm:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n address='" + getAddress() + "'" +
                ",\n status='" + getStatus() + "'" +
                ",\n bank='" + getBank() + "'" +
                ",\n bankOffice='" + getBankOffice() + "'" +
                ",\n employee='" + getEmployee() + "'" +
                ",\n isCashWithdrawalAvailable='" + getCashWithdrawalAvailable() + "'" +
                ",\n isCashDepositAvailable='" + getCashDepositAvailable() + "'" +
                ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n maintenanceCost='" + String.format("%.2f", getMaintenanceCost()) + "'" +
                "\n}";
    }

    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public Status getStatus(){
        return this.status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public Bank getBank(){
        return this.bank;
    }

    public void setBank(Bank bank){
        this.bank = bank;
    }

    public BankOffice getBankOffice(){
        return this.bankOffice;
    }

    public void setBankOffice(BankOffice bankOffice){
        this.bankOffice = bankOffice;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean getCashWithdrawalAvailable(){
        return this.isCashWithdrawalAvailable;
    }

    public void setCashWithdrawalAvailable(boolean cashWithdrawalAvailable){
        this.isCashWithdrawalAvailable = cashWithdrawalAvailable;
    }

    public boolean getCashDepositAvailable(){
        return this.isCashDepositAvailable;
    }

    public void setCashDepositAvailable(boolean cashDepositAvailable){
        this.isCashDepositAvailable = cashDepositAvailable;
    }

    public BigDecimal getTotalMoney(){
        return this.totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney){
        this.totalMoney = totalMoney;
    }

    public BigDecimal getMaintenanceCost(){
        return this.maintenanceCost;
    }

    public void setMaintenanceCost(BigDecimal maintenanceCost){
        this.maintenanceCost = maintenanceCost;
    }

    private void initWithDefaults(){
        id = UUID.randomUUID();
        name = "none";
        address = "none";
        status = Status.NOT_WORKING;
        bank = null;
        bankOffice = null;
        employee = null;
        isCashWithdrawalAvailable = false;
        isCashDepositAvailable = false;
        totalMoney = new BigDecimal("0");
        maintenanceCost = new BigDecimal("0");
    }
}