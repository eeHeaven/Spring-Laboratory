package spring.practice.tobyspring.aop.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.Locale;

public class UpperCaseAdvisor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String ret = (String)invocation.proceed();
        return ret.toUpperCase();
    }
}
