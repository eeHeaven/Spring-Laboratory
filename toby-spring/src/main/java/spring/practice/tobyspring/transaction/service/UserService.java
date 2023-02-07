package spring.practice.tobyspring.transaction.service;

import spring.practice.tobyspring.transaction.domain.Level;
import spring.practice.tobyspring.transaction.domain.User;
import spring.practice.tobyspring.transaction.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository repository = new UserRepository();
    private final UpgradeLevelPolicy levelPolicy = new NormalUpgradeLevelPolicy();

    public void upgradeLevels() throws Exception {
        List<User> users = repository.getAll();
        for(User user: users){
          upgradeLevel(user);
        }
    }
    public void upgradeLevel(User user) {
        if(levelPolicy.canUpgrade(user)){
            Level next = user.getLevel().getNext();
            user.setLevel(next);
            repository.upgrade(user);
        }
    }

}
