package learn.test;

import com.alibaba.fastjson2.JSON;
import learn.user.User;
import learn.utils.BeanUtil;
import learn.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 吴飞群
 * @createTime 2022/05/22
 */
@Slf4j
@Service
public class TestService {

    private final RedisTemplate<String, Object>  redisTemplate;
    private final RedissonClient redissonClient;
    static int count = 0;

    @Autowired
    public TestService(RedisTemplate<String, Object> redisTemplate, RedissonClient redissonClient) {
        this.redisTemplate = redisTemplate;
        this.redissonClient = redissonClient;
    }

    public void test (){

        redisTemplate.opsForValue().set("lock", "1");
    }
    public void test1 (){
        redissonClient.getBucket("abc").set("wufeiqun");
    }

    public static void test2 (){
        //浅拷贝的问题, 任何一个修改了address对象以后, 所有引用的都会变化, 会导致意想不到的结果
        Forms.User pm = new Forms.User("pm", "123", new Forms.Address("china"));
        Forms.User copy = BeanUtil.deepCopy(pm, Forms.User.class);

        System.out.println(JSON.toJSONString(pm));
        System.out.println(JSON.toJSONString(copy));
        System.out.println("**********************");
        pm.getAddress().setCountry("us");
        pm.setUsername("pm-update");
        System.out.println(JSON.toJSONString(pm));
        System.out.println(JSON.toJSONString(copy));
        System.out.println(Forms.User.class.getClassLoader());

    }

    public void test3 (){
        User user = RequestUtil.getCurrentUser();
        log.info("user={}", JSON.toJSONString(user));
    }

    public int[] moveZeros(int[] nums){
        if (nums == null || nums.length == 0){
            return new int[]{};
        }

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                nums[index] = nums[i];
                index++;
            }
        }

        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }

        return nums;
    }

    public static void main(String[] args) {
        String abc = "avxsdsds";
        Integer l = abc.length();
    }

}
