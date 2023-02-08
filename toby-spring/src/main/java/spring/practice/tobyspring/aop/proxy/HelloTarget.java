package spring.practice.tobyspring.aop.proxy;

public class HelloTarget implements Hello{
    static final String GREETING = "hello";

    @Override
    public String getGreeting(){
        return GREETING;
    }
    @Override
    public void sayHello(){
        System.out.println(getGreeting());
    }

    @Override
    public String getDoubleGreeting() {
        return getGreeting()+getGreeting();
    }
}
