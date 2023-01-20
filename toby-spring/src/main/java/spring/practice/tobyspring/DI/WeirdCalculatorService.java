package spring.practice.tobyspring.DI;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeirdCalculatorService implements CalculatorService{

    private final NormalCalculatorService normalCalculatorService;

    @Override
    public int add(int value1, int value2) {
        return normalCalculatorService.add(value1,value2)*2;
    }
}
