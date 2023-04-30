package spring.aop.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

/*
args 포인트컷 선언 관련 테스트
execution 과 다른점: 파라미터의 부모타입을 허용한다 (execution은 허용 안함)
  execution(* *(java.io.Serializable)): 메서드의 시그니처로 판단 (정적)
     args(java.io.Serializable): 런타임에 전달된 인수로 판단 (동적)
 */
public class ArgsTest {

    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello",String.class);
    }
    private AspectJExpressionPointcut pointcut(String expression){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    @Test
    void args(){
        assertThat(pointcut("args(String)").matches(helloMethod,String.class)).isTrue();
        assertThat(pointcut("args(Object)").matches(helloMethod,String.class)).isTrue();
        assertThat(pointcut("args(..)").matches(helloMethod,String.class)).isTrue();
        assertThat(pointcut("args(Object,..)").matches(helloMethod,String.class)).isTrue();
    }


}
