package spring.practice.tobyspring.transaction.domain;

public enum Level {
    GOLD(3,null,null),SILVER(2,GOLD,50),BRONZE(1,SILVER,30);

    private final int value;
    private final Level next;
    private final Integer upgradePolicy;

    Level(int value, Level next, Integer upgradePolicy) {
        this.value = value;
        this.next = next;
        this.upgradePolicy = upgradePolicy;
    }
    public Level getNext(){
        return this.next;
    }
    public int getUpgradePolicy(){
        return this.upgradePolicy;
    }
}
