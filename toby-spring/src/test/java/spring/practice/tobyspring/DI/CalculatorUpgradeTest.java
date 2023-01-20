package spring.practice.tobyspring.DI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorUpgradeTest {

    @Qualifier("weirdCalculatorService")
    @Autowired
    CalculatorService calculatorService;

    @Test
    public void 이상한계산기테스트(){
         int result = calculatorService.add(1,1);
         assert(result == 4);
    }
}
