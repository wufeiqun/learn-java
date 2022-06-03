package learn.test.user;

import learn.test.TestBase;
import learn.user.User;
import learn.user.support.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTest extends TestBase {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
      User user = new User();
      user.setAccount("wufeiqun");
      user.setName("吴飞群");
      userService.insert(user);

    }

}
