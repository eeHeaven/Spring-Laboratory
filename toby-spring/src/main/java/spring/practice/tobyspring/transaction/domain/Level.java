package spring.practice.tobyspring.transaction.domain;

public enum Level {
    GOLD(3,null),SILVER(2,GOLD),BRONZE(1,SILVER);

    private final int value;
    private final Level next;

    Level(int value, Level next) {
        this.value = value;
        this.next = next;
    }
    public Level getNext(){
        return this.next;
    }

}
