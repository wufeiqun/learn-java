package learn.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author: 吴飞群
 * @date: 2021/6/24
 * @time: 下午7:55
 */
@Data
public class Forms {

    @Data
    public static class User{
        @NotBlank(message = "account不能为空")
        public String account;
        @NotBlank(message = "name不能为空")
        public String name;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class UserAddForm extends User{ }


}
