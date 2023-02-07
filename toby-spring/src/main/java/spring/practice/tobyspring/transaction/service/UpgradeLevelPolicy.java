package spring.practice.tobyspring.transaction.service;

import spring.practice.tobyspring.transaction.domain.User;

public interface UpgradeLevelPolicy {

    public boolean canUpgrade(User user);

}
