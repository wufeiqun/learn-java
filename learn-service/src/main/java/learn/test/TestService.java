package learn.test;

import com.alibaba.fastjson2.JSON;
import learn.sort.ListNode;
import learn.user.User;
import learn.utils.BeanUtil;
import learn.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.channels.AsynchronousByteChannel;

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

    public static void main(String[] args) throws InterruptedException {
        String str = "a,b,c,,,";
        String[] ary = str.split(",");
// 预期大于 3，结果是 3
        System.out.println(ary.length);
        System.out.println(JSON.toJSONString(ary));
        ListNode node = new ListNode(1);
        System.out.println(System.getenv());
    }

}
