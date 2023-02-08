package spring.practice.tobyspring.aop.proxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Proxy;

@SpringBootTest
public class ProxyFactoryBeanTest {

    @TestConfiguration
    class Config{
        @Bean
        public ProxyFactoryBean proxyFactoryBean(){
            ProxyFactoryBean pfBean = new ProxyFactoryBean();
            pfBean.setTarget(new HelloTarget());

            NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
            pointcut.setMappedName("get*");

            // proxyFactoryBean에 Advisor 생성해서 넣기
            pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UpperCaseAdvisor()));
            return pfBean;
        }

    }
    @Autowired
    private Hello proxiedHello;

    @Test
    public void 대문자HELLO출력(){
        assert(proxiedHello.getGreeting()).equals("HELLO");
        assert(proxiedHello.getDoubleGreeting()).equals("HELLOHELLO");
    }


}
