package spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import spring.aop.annotation.Retry;

@Slf4j
@Aspect
@Component
public class RetryAspect {

    @Around("@annotation(retry)")
    public Object retry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        int maxTrial = retry.value();
        Exception exceptionHandler = null;
        for(int i = 1; i<=maxTrial;i++){
            try{
                log.info("[retry] {}/{}",i,maxTrial);
                return joinPoint.proceed();
            }catch(Exception e){
                exceptionHandler = e;
            }
        }
        return exceptionHandler;
    }
}
