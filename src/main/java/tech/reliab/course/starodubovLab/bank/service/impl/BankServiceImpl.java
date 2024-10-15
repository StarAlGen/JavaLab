package tech.reliab.course.starodubovLab.bank.service.impl;

import tech.reliab.course.starodubovLab.bank.entity.*;
import tech.reliab.course.starodubovLab.bank.service.BankOfficeService;
import tech.reliab.course.starodubovLab.bank.service.BankService;
import tech.reliab.course.starodubovLab.bank.service.UserService;
import tech.reliab.course.starodubovLab.bank.utils.BigRandom;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Random;

public class BankServiceImpl implements BankService {
    private final Map<Integer, Bank> banksTable = new HashMap<>();
    private final Map<Integer, List<BankOffice>> officesByBankIdTable = new HashMap<>();
    private final Map<Integer, List<User>> usersByBankIdTable = new HashMap<>();
    private BankOfficeService bankOfficeService;
    private UserService userService;

    @Override
    public void setBankOfficeService(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<BankOffice> getAllOfficesByBankId(int id) {
        Bank bank = getBankById(id);
        if (bank != null) {
            return officesByBankIdTable.get(id);
        }
        return new ArrayList<>();
    }

    @Override
    public Bank create(Bank bank) {
        if (bank == null) {
            return null;
        }

        Bank newBank = new Bank(bank.getId(), bank.getName());

        final Random random = new Random();

        newBank.setRating((byte) random.nextInt(Bank.MAX_RATING.intValue() + 1));
        newBank.setTotalMoney(
                BigRandom.between(new BigDecimal("0.0"), new BigDecimal("1.0").multiply(Bank.MAX_TOTAL_MONEY)));
        calculateInterestRate(newBank);

        banksTable.put(newBank.getId(), newBank);
        officesByBankIdTable.put(newBank.getId(), new ArrayList<>());
        usersByBankIdTable.put(newBank.getId(), new ArrayList<>());

        return newBank;
    }

    @Override
    public boolean addEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            employee.setBank(bank);
            bank.setEmployeeCount(bank.getEmployeeCount() + 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            final int newEmployeeCount = bank.getEmployeeCount() - 1;

            if (newEmployeeCount < 0) {
                System.err.println("Error: Bank - cannot remove employee, no employees");
                return false;
            }

            bank.setEmployeeCount(newEmployeeCount);

            return true;
        }
        return false;
    }

    @Override
    public Bank getBankById(int bankId) {
        Bank bank = banksTable.get(bankId);
        if (bank == null) {
            System.err.println("Bank with id " + bankId + " is not found");
        }
        return bank;
    }

    @Override
    public void printBankData(int bankId) {
        Bank bank = getBankById(bankId);
        if (bank == null) {
            return;
        }
        System.out.println("=====================");
        System.out.println(bank);

        List<BankOffice> offices = officesByBankIdTable.get(bankId);
        if (offices != null) {
            System.out.println("Offices:");
            offices.forEach((BankOffice office) -> {
                bankOfficeService.printBankOfficeData(office.getId());
            });
        }
        List<User> users = usersByBankIdTable.get(bankId);
        if (users != null) {
            System.out.println("users:");
            users.forEach((User user) -> {
                userService.printUserData(user.getId(), false);
            });
        }
        System.out.println("=====================");
    }

    @Override
    public boolean deleteBankById(int bankId) {
        return true;
    }

    @Override
    public List<Bank> getAllBanks() {
        return new ArrayList<>(banksTable.values());
    }

    @Override
    public boolean addOffice(int bankId, BankOffice bankOffice) {
        Bank bank = getBankById(bankId);
        if (bank != null && bankOffice != null) {
            bankOffice.setBank(bank);
            bank.setOfficeCount(bank.getOfficeCount() + 1);
            bank.setAtmCount(bank.getAtmCount() + bankOffice.getAtmCount());
            depositMoney(bankId, bankOffice.getTotalMoney());
            List<BankOffice> bankOffices = getAllOfficesByBankId(bankId);
            bankOffices.add(bankOffice);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeOffice(int bankId, BankOffice bankOffice) {
        Bank bank = getBankById(bankId);
        int officeIndex = officesByBankIdTable.get(bankId).indexOf(bankOffice);
        if (bank != null && officeIndex >= 0) {
            final int newOfficeCount = bank.getOfficeCount() - 1;

            if (newOfficeCount < 0) {
                System.err.println("Error: Bank - cannot remove office, no offices");
                return false;
            }

            bank.setOfficeCount(newOfficeCount);

            bank.setAtmCount(bank.getAtmCount() - officesByBankIdTable.get(bankId).get(officeIndex).getAtmCount());
            officesByBankIdTable.get(bankId).remove(officeIndex);

            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(int id, User user) {
        Bank bank = getBankById(id);
        if (bank != null && user != null) {
            user.setBank(bank);
            bank.setUserCount(bank.getUserCount() + 1);
            List<User> users = usersByBankIdTable.get(id);
            users.add(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeUser(Bank bank, User user) {
        if (bank != null && user != null) {
            int newUserCount = bank.getUserCount() - 1;

            if (newUserCount < 0) {
                System.err.println("Error: Bank - cannot remove user, no users");
                return false;
            }

            bank.setUserCount(newUserCount);
            return true;
        }
        return false;
    }

    @Override
    public boolean approveCredit(Bank bank, CreditAccount account, Employee employee) {
        return false;
    }

    @Override
    public BigDecimal calculateInterestRate(Bank bank) {
        if (bank != null) {
            final BigDecimal rating = BigDecimal.valueOf(bank.getRating());

            final BigDecimal centralBankInterestRate = BigRandom.between(new BigDecimal("0.0"), new BigDecimal("1.0"))
                    .multiply(Bank.MAX_INTEREST_RATE);
            final BigDecimal maxBankInterestRateMargin = Bank.MAX_INTEREST_RATE.subtract(centralBankInterestRate);
            final BigDecimal bankInterestRateMargin = (BigRandom.between(new BigDecimal("0.0"), new BigDecimal("1.0"))
                    .multiply(maxBankInterestRateMargin))
                    .multiply((new BigDecimal("110").subtract(rating).divide(new BigDecimal("100"))));
            final BigDecimal interestRate = centralBankInterestRate.add(bankInterestRateMargin);

            bank.setInterestRate(interestRate);
            return interestRate;
        }
        return new BigDecimal("0");
    }

    @Override
    public boolean depositMoney(int id, BigDecimal amount) {
        Bank bank = getBankById(id);
        if (bank == null) {
            System.err.println("Error: Bank - cannot deposit money to uninitialized bank");
            return false;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: Bank - cannot deposit money - deposit amount must be positive");
            return false;
        }

        bank.setTotalMoney(bank.getTotalMoney().add(amount));
        return true;
    }

    @Override
    public boolean withdrawMoney(int id, BigDecimal amount) {
        Bank bank = getBankById(id);
        if (bank == null) {
            System.err.println("Error: Bank - cannot withdraw money, bank is null");
            return false;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: Bank - cannot withdraw money - withdraw amount must be positive");
            return false;
        }

        if (bank.getTotalMoney().compareTo(amount) < 0) {
            System.err.println("Error: Bank - cannot withdraw money - bank does not have enough money");
            return false;
        }

        bank.setTotalMoney(bank.getTotalMoney().subtract(amount));
        return true;

    }
}
