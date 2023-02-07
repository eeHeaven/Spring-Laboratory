package spring.practice.tobyspring.transaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.practice.tobyspring.transaction.domain.Level;
import spring.practice.tobyspring.transaction.domain.User;
import spring.practice.tobyspring.transaction.repository.UserRepository;
import spring.practice.tobyspring.transaction.service.NormalUpgradeLevelPolicy;
import spring.practice.tobyspring.transaction.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BatchUpgradeTest {

    private static UserRepository userRepository;
    private static final List<User> users = List.of(new User("test",Level.SILVER,0));

    @BeforeEach
    public void init(){
        userRepository = mock(UserRepository.class);
        when(userRepository.getAll()).thenReturn(this.users);
    }

   static class TestService extends UserService {
        private String id;
        private TestService(String id){
            super(userRepository, new NormalUpgradeLevelPolicy());
            this.id = id;
        }
        public void upgradeLevel(User user){
            if(user.getId().equals(this.id)) throw new TestUpgradeException();
            super.upgradeLevel(user);
        }
    }
    @Test
    public void 강제로예외내기() throws Exception {
       //given
       UserService service = new TestService("test");
       //then
       Assertions.assertThrows(RuntimeException.class, service::upgradeLevels);
    }
}
class TestUpgradeException extends RuntimeException{

}
