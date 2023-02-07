package spring.practice.tobyspring.exception;

import org.junit.jupiter.api.Test;

public class ExceptionRetrialTest {

    @Test
    public void 재시도테스트() {
        int maxRetry = 5;
        while (maxRetry--> 0) { // 이렇게 하면 하나씩 깎이는구나!! 따로 maxRetry-- 안해줘도 됨
            try {
                test("fail");
                return;
            } catch (TestExcpetion e) {
                System.out.println("에러 발생, 남은 테스트 횟수 : "+maxRetry);
            }
        }
        throw new RuntimeException("재시도 실패");
    }

    private void test(String value) throws TestExcpetion{
        if(value.equals("pass")) return;
        else throw new TestExcpetion();
    }
}
