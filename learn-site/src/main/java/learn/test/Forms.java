package learn.test;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;

/**
 * @author 吴飞群
 * @createTime 2022/06/01
 */
public class Forms {
    @Data
    @ToString
    public static class AddUser {
        @Max(value=10, message="ID不能大于10")
        private int id;
        private String account;
        private String username;
    }
}
