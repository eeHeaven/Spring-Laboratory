package spring.practice.tobyspring.transaction.service;

import spring.practice.tobyspring.transaction.domain.Level;
import spring.practice.tobyspring.transaction.domain.User;

public class NormalUpgradeLevelPolicy implements UpgradeLevelPolicy{
    private static final int MIN_POINT_FOR_SILVER = 30;
    private static final int MIN_POINT_FOR_GOLD = 50;

    @Override
    public boolean canUpgrade(User user) {
        Level currLevel = user.getLevel();
        switch (currLevel){
            case BRONZE: return (user.getPoint()>=MIN_POINT_FOR_SILVER);
            case SILVER: return (user.getPoint()>=MIN_POINT_FOR_GOLD);
            default: return false;
        }
    }
}
