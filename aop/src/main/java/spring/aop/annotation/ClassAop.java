package spring.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
aop 적용 실험용 annotation
적용 대상: type (클래스)
 */
@Target(ElementType.TYPE)//annotation을 부착할 위치
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassAop {
}
