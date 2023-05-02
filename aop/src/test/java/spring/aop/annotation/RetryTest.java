package spring.aop.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RetryTest {

    @Autowired
    SampleService sampleService;

    @Test
    void execute(){
        for(int i = 0; i<10;i++){
            sampleService.save("test"+i);
        }
    }
}
