package spring.aop.member;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

/*
within 키워드로 포인트컷 잡기 테스트
within: 특정 타입 내의 조인프인트들로 매칭을 제한
execution과 다른 점 = 부모타입 선정 불가!
 */
@Slf4j
public class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello",String.class);
    }

    @Test
    void withinSubPackage(){
        pointcut.setExpression("within(spring.aop..*)");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    @DisplayName("within에서는 부모타입 선정 불가")
    void falseWithSuperType(){
        pointcut.setExpression("within(spring.aop.member.MemberService)");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }
}
