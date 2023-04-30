package spring.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV4 {
    // 포인트컷 시그니처 적용
    @Around("spring.aop.order.aop.PointCuts.allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("log: {}",joinPoint.getSignature());
        return joinPoint.proceed();
    }
    @Around("spring.aop.order.aop.PointCuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            log.info("트랜잭션 시작");
            Object result = joinPoint.proceed();
            log.info("commit");
            return result;
        }catch(Exception e){
            log.info("롤백");
            throw e;
        }finally{
            log.info("release");
        }
    }
}
