package spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class TraceAspect {

    @Around("@annotation(spring.aop.annotation.Trace)")
    public Object logTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        log.info("[trace] type ={} , args ={}",joinPoint.getSignature(),args);
        return joinPoint.proceed();
    }
}
