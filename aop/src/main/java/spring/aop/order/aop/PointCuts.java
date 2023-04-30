package spring.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    //포인트컷 시그니처 정의
    @Pointcut("execution(* spring.aop.order..*(..))")
    public void allOrder(){};

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){};

    @Pointcut("allOrder() && allService()")
    public void orderAndService(){};
}
