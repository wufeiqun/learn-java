package learn.admin.support;

import learn.AbstractController;
import learn.pojo.CommonVo;
import learn.user.support.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这里放一些管理员常用的一些操作, 比如打开/关闭某些功能, 刷数据等
 * 简单的通过postman就可以完成, 加一个特殊的密码
 * 当然也可以放到后管的页面中
 */
@RestController
@RequestMapping("/admin/")
@Slf4j
public class AdminController extends AbstractController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/add")
    public CommonVo<Integer> userAdd() {
        return CommonVo.success();
    }

}
