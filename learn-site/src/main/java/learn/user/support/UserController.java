package learn.user.support;

import learn.pojo.CommonVo;
import learn.user.Forms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user/")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserFacade userFacade;

    @Autowired
    public UserController(UserService userService, UserFacade userFacade) {
        this.userService = userService;
        this.userFacade = userFacade;
    }

    @GetMapping("/add")
    public CommonVo<Integer> addUser(@RequestBody @Valid Forms.UserAddForm form, BindingResult bindingResult) {
        int count = userFacade.insert(form);
        return CommonVo.success(count);
    }

}
