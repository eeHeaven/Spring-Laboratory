package spring.practice.tobyspring.transaction.service;

import spring.practice.tobyspring.transaction.domain.Level;
import spring.practice.tobyspring.transaction.domain.User;
import spring.practice.tobyspring.transaction.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository repository = new UserRepository();

    public void upgradeLevels() throws Exception {
        List<User> users = repository.getAll();
        for(User user: users){
            if(canUpgrade(user)){
                Level next = user.getLevel().getNext();
                user.setLevel(next);
            }
        }
    }
    private boolean canUpgrade(User user){
        Integer upgradePolicy = user.getLevel().getUpgradePolicy();
        if(upgradePolicy == null) return false;
        if(user.getPoint()>=upgradePolicy) return true;
        else return false;
    }
}
