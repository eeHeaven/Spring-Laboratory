package spring.practice.tobyspring.aop.proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class HelloFactoryBean implements FactoryBean<Object> {

    @Override
    public Object getObject() throws Exception {
        return (Hello) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{Hello.class},
                new UpperCaseHandler(new HelloTarget())
        );
    }

    @Override
    public Class<?> getObjectType() {
        return Hello.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
