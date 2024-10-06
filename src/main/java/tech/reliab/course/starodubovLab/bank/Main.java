package tech.reliab.course.starodubovLab.bank;

import tech.reliab.course.starodubovLab.bank.entity.*;
import tech.reliab.course.starodubovLab.bank.service.*;
import tech.reliab.course.starodubovLab.bank.service.impl.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Main {
    public static void main(String[]args){
        BankService bankService = new BankServiceImpl();
        Bank bank = bankService.create(new Bank("Ozon Bank"));
        System.out.println(bank);

        BankOfficeService bankOfficeService = new BankOfficeServiceImpl();
        BankOffice bankOffice = bankOfficeService.create(new BankOffice("Main office of Ozon Bank","123112, Moscow, Presnenskaya embankment, 1. 10",
                true,true,10,bank,true,true,true, bank.getTotalMoney(),new BigDecimal("10000")));
        System.out.println(bankOffice);

        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = employeeService.create(new Employee("Ivanov Ivan Ivanovich", LocalDate.of(1984,1,1),Employee.Job.Manager,bank,false,bankOffice,
                false, new BigDecimal("150000")));
        System.out.println(employee);

        AtmService atmService = new AtmServiceImpl();
        BankAtm bankAtm = atmService.create(new BankAtm("ATM of Ozon Bank",bankOffice.getAddress(),BankAtm.Status.WORKING,bank,bankOffice,employee,true,true,
                bankOffice.getTotalMoney().divide(new BigDecimal(String.valueOf(bankOffice.getAtmCount())),RoundingMode.HALF_UP),new BigDecimal("2000")));
        System.out.println(bankAtm);

        UserService userService = new UserServiceImpl();
        User user = userService.create(new User("Ivanov Sergey Sergeyevich",LocalDate.of(2000,10,10),"Ozon Bank",new BigDecimal("100000"),
                bank,new BigDecimal("1000")));
        System.out.println(user);

        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl();
        PaymentAccount paymentAccount = paymentAccountService.create(new PaymentAccount(user,bank,new BigDecimal("10000")));
        System.out.println(paymentAccount);

        CreditAccountService creditAccountService = new CreditAccountServiceImpl();
        CreditAccount creditAccount = creditAccountService.create(new CreditAccount(user,bank,LocalDate.of(2020,1,1),LocalDate.of(2021,1,1),
                12,new BigDecimal("100000"),new BigDecimal("100000"),new BigDecimal("8500"),new BigDecimal("2"),employee,paymentAccount));
        System.out.println(creditAccount);
    }
}
