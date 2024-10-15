package tech.reliab.course.starodubovLab.bank;

import tech.reliab.course.starodubovLab.bank.entity.*;
import tech.reliab.course.starodubovLab.bank.service.*;
import tech.reliab.course.starodubovLab.bank.service.impl.*;
import tech.reliab.course.starodubovLab.bank.utils.Consts;
import static tech.reliab.course.starodubovLab.bank.utils.Consts.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        new Consts();

        // Создание сервисов
        BankService bankService = new BankServiceImpl();
        BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankService);
        bankService.setBankOfficeService(bankOfficeService);
        EmployeeService employeeService = new EmployeeServiceImpl(bankOfficeService);
        AtmService atmService = new AtmServiceImpl(bankOfficeService);
        UserService userService = new UserServiceImpl(bankService);
        bankService.setUserService(userService);
        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(userService);
        CreditAccountService creditAccountService = new CreditAccountServiceImpl(userService);

        // Создадим банки
        bankService.create(new Bank("Iron Bank of Braavos"));
        bankService.create(new Bank("Bank of Casterly Rock"));
        bankService.create(new Bank("Lanisport Bank"));
        bankService.create(new Bank("Bank of Slaver's Bay"));
        bankService.create(new Bank("Bank of Yunkai"));

        // Создание офисов в каждом банке
        List<Bank> banks = bankService.getAllBanks();
        for (Bank bank : banks) {
            for (int i = 1; i <= 3; i++) {
                bankOfficeService.create(new BankOffice(
                        "Office №" + i + " of " + bank.getName(),
                        "Westeros, Red Keep st., " + i,
                        bank,
                        true,
                        true,
                        0,
                        true,
                        true,
                        true,
                        new BigDecimal("20000"),
                        new BigDecimal(100 * i)));
            }
        }

        // Добавление сотрудников в каждый офис
        List<BankOffice> offices = bankOfficeService.getAllOffices();
        for (BankOffice office : offices) {
            for (int i = 1; i <= 5; i++) {
                employeeService.create(new Employee(
                        PeopleNames.get(random.nextInt(PeopleNames.size())),
                        LocalDate.of(random.nextInt(1990, 2003), random.nextInt(1, 13),
                                random.nextInt(1, 29)),
                        Employee.Job.getRandom(),
                        office.getBank(),
                        true,
                        office,
                        true,
                        new BigDecimal("300")));
            }
        }

        // Добавление банкоматов в каждый офис
        for (BankOffice office : offices) {
            for (int i = 1; i <= 3; i++) {
                atmService.create(new BankAtm(
                        "Atm " + i,
                        office.getAddress(),
                        BankAtm.Status.WORKING,
                        office.getBank(),
                        office,
                        bankOfficeService.getAllEmployeesByOfficeId(office.getId())
                                .get(random.nextInt(bankOfficeService
                                        .getAllEmployeesByOfficeId(
                                                office.getId())
                                        .size())),
                        true,
                        true,
                        new BigDecimal("0"),
                        new BigDecimal(random.nextDouble() * 25)));
            }
        }

        // Добавление клиентов в каждый банк
        for (Bank bank : banks) {
            for (int i = 1; i <= 5; i++) {
                userService.create(
                        new User(
                                PeopleNames.get(random.nextInt(PeopleNames.size())),
                                LocalDate.of(random.nextInt(200, 300),
                                        random.nextInt(1, 13),
                                        random.nextInt(1, 29)),
                                CompanyNames.get(random.nextInt(CompanyNames.size())),
                                new BigDecimal(random.nextDouble() * 10000),
                                bank,
                                new BigDecimal(random.nextInt(10000))));
            }
        }

        // Добавление платежных счетов каждому клиенту
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            for (int i = 1; i <= 2; i++) {
                paymentAccountService.create(new PaymentAccount(
                        user,
                        user.getBank(),
                        new BigDecimal(random.nextDouble() * 10000)));
            }
        }

        // Добавление кредитных счетов каждому клиенту
        for (User user : users) {
            for (int i = 1; i <= 2; i++) {
                List<BankOffice> bankOffices = bankService
                        .getAllOfficesByBankId(user.getBank().getId());
                BankOffice randomOffice = bankOffices.get(random.nextInt(bankOffices.size()));
                List<Employee> officeEmployees = bankOfficeService
                        .getAllEmployeesByOfficeId(randomOffice.getId());
                Employee randomEmployee = officeEmployees.get(random.nextInt(officeEmployees.size()));

                creditAccountService.create(new CreditAccount(
                        user,
                        user.getBank(),
                        LocalDate.of(2023, 10, 1),
                        LocalDate.of(2026, 10, 1),
                        36,
                        new BigDecimal("2600"),
                        new BigDecimal("2600"),
                        new BigDecimal("100"),
                        user.getBank().getInterestRate(),
                        randomEmployee,
                        userService.getAllPaymentAccountsByUserId(user.getId())
                                .get(random.nextInt(userService
                                        .getAllPaymentAccountsByUserId(
                                                user.getId())
                                        .size()))));
            }
        }

        System.out.println("\nLab #2.");

        while (true) {
            System.out.println("\nPick an action: ");
            System.out.println("b - check bank data by bank id");
            System.out.println("c - check user data by user id");
            System.out.println("q - quit program");

            String action = scanner.nextLine();

            if (action.equals("b")) {
                System.out.println(
                        "Number of banks in the system: " + bankService.getAllBanks().size());
                for (Bank bank : bankService.getAllBanks()) {
                    System.out.println("id: " + bank.getId() + " - " + bank.getName());
                }
                System.out.println("Enter bank id:");
                int bankIdToPrint = scanner.nextInt();
                scanner.nextLine();
                bankService.printBankData(bankIdToPrint);
            } else if (action.equals("c")) {
                System.out.println(
                        "Number of users in the system: "
                                + userService.getAllUsers().size());
                for (User user : userService.getAllUsers()) {
                    System.out.println("id: " + user.getId() + " - " + user.getName());
                }
                System.out.println("Enter user id:");
                int userIdToPrint = scanner.nextInt();
                scanner.nextLine();
                userService.printUserData(userIdToPrint, true);
            } else if (action.equals("q")) {
                break;
            } else {
                System.out.println("Error: unknown action. Please, try again");
            }
        }

        scanner.close();
    }
}