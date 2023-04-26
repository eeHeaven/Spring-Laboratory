package spring.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

    //포인트컷 시그니처 정의
    @Pointcut("execution(* spring.aop.order..*(..))")
    private void allOrder(){};

    // 포인트컷 시그니처 적용
    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("log: {}",joinPoint.getSignature());
        return joinPoint.proceed();
    }

}
