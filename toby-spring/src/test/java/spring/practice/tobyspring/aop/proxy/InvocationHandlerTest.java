package spring.practice.tobyspring.aop.proxy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class InvocationHandlerTest {

    private static Hello proxiedHello;
    private static HelloTarget target = new HelloTarget();

    @BeforeEach
    public void 프록시객체생성(){
        proxiedHello = (Hello) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{Hello.class},
                new UpperCaseHandler(target)
        );
    }

    @Test
    public void 대문자HELLO출력(){
        assert(proxiedHello.getGreeting()).equals("HELLO");
        assert(target.getGreeting()).equals("hello");
    }


    @Test
    public void InvocationHandler에지정된리턴값을주는메서드만가능(){
        Assertions.assertThrows(NullPointerException.class,proxiedHello::sayHello);
    }

    @Test
    public void 내부참조테스트(){
        assert(proxiedHello.getDoubleGreeting()).equals("HELLOHELLO");
    }
}
