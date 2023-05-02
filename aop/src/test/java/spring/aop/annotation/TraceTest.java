package spring.aop.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TraceTest {

    @Autowired
    SampleService sampleService;

    @Test
    void execute(){
        sampleService.save("test1");
        sampleService.save("test2");
        sampleService.save("test3");
    }
}
