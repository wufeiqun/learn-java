package learn.test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 吴飞群
 * @createTime 2022/05/26
 */
public class Forms {
    @Data
    @AllArgsConstructor
    public static class User {
        private String username;
        private String password;
        private Address address;
    }

    @Data
    @AllArgsConstructor
    public static class Address {
        private String country;
    }
}
