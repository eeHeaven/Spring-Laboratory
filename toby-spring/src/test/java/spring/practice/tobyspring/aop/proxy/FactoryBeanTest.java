package spring.practice.tobyspring.aop.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FactoryBeanTest {

    @Autowired
    Hello proxiedHello;

    @Test
    public void 대문자HELLO출력(){
        assert(proxiedHello.getGreeting()).equals("HELLO");
    }
}
