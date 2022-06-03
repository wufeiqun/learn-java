package learn.user.support;

import learn.user.User;

import java.util.List;

/**
 * @author 吴飞群
 * @createTime 2022/05/16
 */
public interface ExUserMapper extends UserMapper{
    int batchInsert(List<User> userList);
}
