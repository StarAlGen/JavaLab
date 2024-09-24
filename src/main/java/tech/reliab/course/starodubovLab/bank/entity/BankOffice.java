package tech.reliab.course.starodubovLab.bank.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class BankOffice {
    private UUID id;
    private String name;
    private String address;
    private boolean isWorking;
    private boolean isAtmPlaceable;
    private int atmCount;
    private Bank bank;
    private boolean isCreditAvailable;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private BigDecimal totalMoney;
    private BigDecimal rentPrice;

    public BankOffice(String name, String address){
        initWithDefaults();
        this.name = name;
        this.address = address;
    }

    public BankOffice(UUID id, String name, String address, boolean isWorking, boolean isAtmPlaceable, int atmCount, Bank bank, boolean isCreditAvailable, boolean isCashWithdrawalAvailable,
                      boolean isCashDepositAvailable, BigDecimal totalMoney, BigDecimal rentPrice){
        this.id = id;
        this.name = name;
        this.address = address;
        this.isWorking = isWorking;
        this.isAtmPlaceable = isAtmPlaceable;
        this.atmCount = atmCount;
        this.bank = bank;
        this.isCreditAvailable = isCreditAvailable;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.rentPrice = rentPrice;
    }

    public BankOffice(String name, String address, boolean isWorking, boolean isAtmPlaceable, int atmCount, Bank bank, boolean isCreditAvailable, boolean isCashWithdrawalAvailable,
                      boolean isCashDepositAvailable, BigDecimal totalMoney, BigDecimal rentPrice){
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.isWorking = isWorking;
        this.isAtmPlaceable = isAtmPlaceable;
        this.atmCount = atmCount;
        this.bank = bank;
        this.isCreditAvailable = isCreditAvailable;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.rentPrice = rentPrice;
    }

    public BankOffice(BankOffice bankOffice){
        this.id = bankOffice.id;
        this.name = bankOffice.name;
        this.address = bankOffice.address;
        this.isWorking = bankOffice.isWorking;
        this.isAtmPlaceable = bankOffice.isAtmPlaceable;
        this.atmCount = bankOffice.atmCount;
        this.bank = bankOffice.bank;
        this.isCreditAvailable = bankOffice.isCreditAvailable;
        this.isCashWithdrawalAvailable = bankOffice.isCashWithdrawalAvailable;
        this.isCashDepositAvailable = bankOffice.isCashDepositAvailable;
        this.totalMoney = bankOffice.totalMoney;
        this.rentPrice = bankOffice.rentPrice;
    }

    @Override
    public String toString() {
        return "BankOffice:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n address='" + getAddress() + "'" +
                ",\n bank='" + getBank() + "'" +
                ",\n isWorking='" + getIsWorking() + "'" +
                ",\n isAtmPlaceable='" + getIsAtmPlaceable() + "'" +
                ",\n atmCount='" + getAtmCount() + "'" +
                ",\n isCreditAvailable='" + getIsCreditAvailable() + "'" +
                ",\n isCashWithdrawalAvailable='" +  getCashWithdrawalAvailable() + "'" +
                ",\n isCashDepositAvailable='" + getCashDepositAvailable() + "'" +
                ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n rentPrice='" + String.format("%.2f", getRentPrice()) + "'" +
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

    public Bank getBank(){
        return this.bank;
    }

    public void setBank(Bank bank){
        this.bank = bank;
    }

    public boolean getIsWorking(){
        return this.isWorking;
    }

    public void setIsWorking(boolean isWorking){
        this.isWorking = isWorking;
    }

    public boolean getIsAtmPlaceable(){
        return this.isAtmPlaceable;
    }

    public void setIsAtmPlaceable(boolean isAtmPlaceable){
        this.isAtmPlaceable = isAtmPlaceable;
    }

    public int getAtmCount(){
        return this.atmCount;
    }

    public void setAtmCount(int atmCount){
        this.atmCount = atmCount;
    }

    public boolean getIsCreditAvailable(){
        return this.isCreditAvailable;
    }

    public void setCreditAvailable(boolean creditAvailable) {
        isCreditAvailable = creditAvailable;
    }

    public boolean getCashWithdrawalAvailable(){
        return this.isCashWithdrawalAvailable;
    }

    public void setCashWithdrawalAvailable(boolean cashWithdrawalAvailable) {
        isCashWithdrawalAvailable = cashWithdrawalAvailable;
    }

    public boolean getCashDepositAvailable(){
        return this.isCashDepositAvailable;
    }

    public void setCashDepositAvailable(boolean cashDepositAvailable) {
        isCashDepositAvailable = cashDepositAvailable;
    }

    public BigDecimal getTotalMoney(){
        return this.totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getRentPrice(){
        return this.rentPrice;
    }

    public void setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
    }

    private void initWithDefaults(){
        id = UUID.randomUUID();
        name = "none";
        address = "none";
        isWorking = false;
        isAtmPlaceable = false;
        atmCount = 0;
        bank = null;
        isCreditAvailable = false;
        isCashWithdrawalAvailable = false;
        isCashDepositAvailable = false;
        totalMoney = new BigDecimal("0");
        rentPrice = new BigDecimal("0");
    }
}
