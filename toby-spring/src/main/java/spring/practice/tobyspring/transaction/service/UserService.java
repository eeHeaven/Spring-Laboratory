package spring.practice.tobyspring.transaction.service;

import lombok.RequiredArgsConstructor;
import spring.practice.tobyspring.transaction.domain.Level;
import spring.practice.tobyspring.transaction.domain.User;
import spring.practice.tobyspring.transaction.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UpgradeLevelPolicy levelPolicy;

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
