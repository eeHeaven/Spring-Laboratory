package spring.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Slf4j
@Aspect
public class AspectV1 {

    //포인트컷 설정
    @Around("execution(* spring.aop.order..*(..))")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{ //어드바이스
        log.info("beforeLog: {}",joinPoint.getSignature());
        return joinPoint.proceed(); // 실제 타겟 호출
    }
}
