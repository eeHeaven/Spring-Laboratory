package spring.aop.member;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;
/*
포인트컷 설정 execution 키워드에 대한 테스트
매칭 조건은 아래와 같음 (?는 생략 가능을 의미)
1. 접근제어자 ?
2. 반환타입
3. 선언 타입 ? ex) spring.aop.member.MemberServiceImpl
4. 메서드 이름
5. 파라미터
6. 예외 ?
 */
@Slf4j
public class ExecutionTest {

    //포인트컷 실험을 위해 사용
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello",String.class);
    }

    //포인트컷 실험을 위해 구체적인 패키지 경로를 가져오기
    @Test
    void printMethod(){
        /*
        public java.lang.String spring.aop.member.MemberServiceImpl.hello(java.lang.String)
         */
        log.info("helloMethod = {}",helloMethod);
    }

    @Test
     void exactMatch(){
        pointcut.setExpression("execution( public java.lang.String spring.aop.member.MemberServiceImpl.hello(java.lang.String))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //가장 많이 생략한 포인트컷
    // ..은 갯수 상관없이 아무거나 들어와도 된다는 의미
    @Test
    void allMath(){
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    // 메서드 이름 매치 관련 테스트
    @Test
    void nameMatchTrue(){
        pointcut.setExpression("execution(* *el*(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test
    void nameMatchFalse(){
        pointcut.setExpression("execution(* *nope*(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }

    //패키지 매치 관련 테스트
    @Test
    void packageMatchWithNoTypeNoMethodName(){
        pointcut.setExpression("execution(* spring.aop.member.*.*(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    // .: 정확하게 해당 위치의 패키지
    @Test
    void packageMatchWithoutSubPackage(){
        pointcut.setExpression("execution(* spring.aop.*.*(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }
    // ..: 해당 위치의 패키지와 그 하위 패키지 포함
    @Test
    void packageMatchWithSubPackage(){
        pointcut.setExpression("execution(* spring..*.*(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //타입 매치 테스트 (부모 타입도 허용)
    @Test
    void TypeMatchSuperType(){
        pointcut.setExpression("execution(* spring.aop.member.MemberService.*(..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
    @Test //단, 부모 타입으로 매칭할 경우 부모 타입에서 정의된 메서드만 적용 가능
    void TypeMatchInternalMethodFalse() throws NoSuchMethodException {
        pointcut.setExpression("execution(* spring.aop.member.MemberService.*(..))");
        Method internal = MemberServiceImpl.class.getMethod("internal",String.class);
        assertThat(pointcut.matches(internal,MemberServiceImpl.class)).isFalse();
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //파라미터 매치 테스트
    //(String), (String, xx), (String, xx, xx) 전부 허용
    @Test
    void argsMatchComplex(){
        pointcut.setExpression("execution(* *(String,..))");
        assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

}
