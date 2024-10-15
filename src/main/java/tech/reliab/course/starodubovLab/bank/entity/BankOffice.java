package tech.reliab.course.starodubovLab.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class BankOffice {
    private static int currentId;
    private int id;
    private String name;
    private String address;
    private Bank bank;
    private boolean isWorking;
    private boolean isAtmPlaceable;
    private int atmCount;
    private boolean isCreditAvailable;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private BigDecimal totalMoney;
    private BigDecimal rentPrice;

    private void initId() {
        id = currentId++;
    }

    public BankOffice(String name, String address) {
        initId();
        initWithDefaults();
        this.name = name;
        this.address = address;
    }

    public BankOffice(String name, String address, Bank bank, boolean isWorking, boolean isAtmPlaceable,
                      int atmCount, boolean isCreditAvailable, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable,
                      BigDecimal totalMoney, BigDecimal rentPrice) {
        initId();
        initWithDefaults();
        this.name = name;
        this.address = address;
        this.bank = bank;
        this.isWorking = isWorking;
        this.isAtmPlaceable = isAtmPlaceable;
        this.atmCount = atmCount;
        this.isCreditAvailable = isCreditAvailable;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.rentPrice = rentPrice;
    }

    public BankOffice(BankOffice bankOffice) {
        this.id = bankOffice.id;
        this.name = bankOffice.name;
        this.address = bankOffice.address;
        this.bank = new Bank(bankOffice.bank);
        this.isWorking = bankOffice.isWorking;
        this.isAtmPlaceable = bankOffice.isAtmPlaceable;
        this.atmCount = bankOffice.atmCount;
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
                ",\n bank='" + getBank().getName() + "'" +
                ",\n isWorking='" + isWorking() + "'" +
                ",\n isAtmPlaceable='" + isAtmPlaceable() + "'" +
                ",\n atmCount='" + getAtmCount() + "'" +
                ",\n isCreditAvailable='" + isCreditAvailable() + "'" +
                ",\n isCashWithdrawalAvailable='" + isCashWithdrawalAvailable() + "'" +
                ",\n isCashDepositAvailable='" + isCashDepositAvailable() + "'" +
                ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n rentPrice='" + String.format("%.2f", getRentPrice()) + "'" +
                "\n}";
    }

    private void initWithDefaults() {
        name = "No name";
        address = "No address";
        bank = null;
        isWorking = false;
        isAtmPlaceable = false;
        atmCount = 0;
        isCreditAvailable = false;
        isCashWithdrawalAvailable = false;
        isCashDepositAvailable = false;
        totalMoney = new BigDecimal("0");
        rentPrice = new BigDecimal("0");
    }
}
