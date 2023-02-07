package spring.practice.tobyspring.transaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.practice.tobyspring.transaction.domain.User;
import spring.practice.tobyspring.transaction.service.UserService;

public class BatchUpgradeTest {

   static class TestService extends UserService {
        private String id;
        private TestService(String id){
            this.id = id;
        }
        public void upgradeLevel(User user){
            if(user.getId().equals(this.id)) throw new TestUpgradeException();
            super.upgradeLevel(user);
        }
    }
    @Test
    public void 트랜잭션테스트() throws Exception {
       //given
       UserService service = new TestService("test");
       //then
       Assertions.assertThrows(RuntimeException.class, service::upgradeLevels);
    }
}
class TestUpgradeException extends RuntimeException{

}
