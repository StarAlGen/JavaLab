package tech.reliab.course.starodubovLab.bank.service.impl;

import tech.reliab.course.starodubovLab.bank.entity.Bank;
import tech.reliab.course.starodubovLab.bank.entity.User;
import tech.reliab.course.starodubovLab.bank.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User create(User user){
        if  (user == null){
            return null;
        }
        if(user.getBank() == null){
            System.err.println("Error: User - must have bank");
            return null;
        }
        return new User(user);
    }

    @Override
    public boolean changeBank(User user, Bank bank){
        if (user != null && bank != null){
            if (user.getBank() == bank){
                return false;
            }
            user.getBank().setUserCount(user.getBank().getUserCount() - 1);
            user.setBank(bank);
            return true;
        }
        return false;
    }
}
