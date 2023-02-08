package spring.practice.tobyspring.aop.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.util.PatternMatchUtils;

@SpringBootTest
public class DefaultAdvisorAutoProxyCreatorTest {

    @TestConfiguration
    class Config{
        @Bean
        public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
            return new DefaultAdvisorAutoProxyCreator();
        }
        @Bean
        public NameMatchMethodPointcut nameMatchMethodPointcut(){
            return new NameMatchClassMethodPoint();
        }
    }
    public static class NameMatchClassMethodPoint extends NameMatchMethodPointcut {
        public void setMappedClassName(String mappedClassName){
            this.setClassFilter(new SimpleClassFilter(mappedClassName));
        }
         static class SimpleClassFilter implements ClassFilter {
            String mappedName;
            private SimpleClassFilter(String mappedName){
                this.mappedName = mappedName;
            }
            public boolean matches(Class<?> clazz){
                return PatternMatchUtils.simpleMatch(mappedName,clazz.getSimpleName());
            }
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
