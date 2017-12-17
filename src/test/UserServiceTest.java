package test;

import com.account.domain.User;
import com.account.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends SpringTestCase {
    @Autowired
    private UserService userService ;
    Logger logger = Logger.getLogger(UserServiceTest.class);
    @Test
    public void selectUserByIdTest(){
        User user = userService.getUserById(1);
        logger.debug("结果"+user.getName());
    }
}
