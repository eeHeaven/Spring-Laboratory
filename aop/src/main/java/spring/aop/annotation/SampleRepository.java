package spring.aop.annotation;

import org.springframework.stereotype.Repository;

@Repository
public class SampleRepository {

    static int seq = 0;

    public String save(String id){
        seq++;
        if(seq % 5 == 0) throw new IllegalStateException("예외 발생");
        return "ok";
    }
}
