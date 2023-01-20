package spring.practice.tobyspring.DI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorWithoutSpringTest {

    private CalculatorService calculatorService;
    private int value1;
    private int value2;

    @BeforeEach
    public void valueSetting(){
        value1 = 1;
        value2 = 2;
    }

    @Test
    public void 정상계산기테스트(){
        calculatorService = new NormalCalculatorService();
        int result = calculatorService.add(value1, value2);
        assert(result == (value1+value2));
    }
    @Test
    public void 이상한계산기테스트(){
        calculatorService = new WeirdCalculatorService(new NormalCalculatorService());
        int result = calculatorService.add(value1, value2);
        assert(result != (value1+value2));
        assert(result == (value1+value2)*2);
    }
}
