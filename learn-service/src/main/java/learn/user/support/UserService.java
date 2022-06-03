package learn.user.support;

import learn.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author:吴飞群
 * @date: 2021/6/24
 * @time: 下午8:00
 */
@Service
@Slf4j
public class UserService {

    private final ExUserMapper userMapper;

    @Autowired
    public UserService(ExUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int insert(User user){
        return userMapper.insertSelective(user);
    }

    public int batchInsert(List<User> userList){
        if (CollectionUtils.isEmpty(userList)){
            return 0;
        }

        return userMapper.batchInsert(userList);
    }



}
