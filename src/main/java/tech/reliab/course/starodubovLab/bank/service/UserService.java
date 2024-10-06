package tech.reliab.course.starodubovLab.bank.service;

import tech.reliab.course.starodubovLab.bank.entity.Bank;
import tech.reliab.course.starodubovLab.bank.entity.User;

public interface UserService {
    User create(User user);

    boolean changeBank(User user, Bank bank);
}
