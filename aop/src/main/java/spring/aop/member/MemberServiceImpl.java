package spring.aop.member;

import org.springframework.stereotype.Service;
import spring.aop.annotation.ClassAop;
import spring.aop.annotation.MethodAop;

@ClassAop
@Service
public class MemberServiceImpl implements MemberService{
    @Override
    @MethodAop("test")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param){
        return "ok";
    }
}
