package spring.practice.tobyspring.DI;

import org.springframework.stereotype.Service;

@Service
public class NormalCalculatorService implements CalculatorService {

    public int add(int value1, int value2){
        return value1+value2;
    }
}
