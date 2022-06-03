package learn.user.support;

import learn.user.Forms;
import learn.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 吴飞群
 * @createTime 2022/05/17
 */
@Service
public class UserFacade {
    private final UserService userService;

    @Autowired
    public UserFacade(UserService userService) {
        this.userService = userService;
    }

    /**
     * 创建单个用户
     */
    public int insert(Forms.UserAddForm form){
        User user = new User();
        user.setAccount(form.getAccount());
        user.setName(form.getName());

        return userService.insert(user);
    }
}
