package learn.test;

import com.alibaba.fastjson2.JSON;
import learn.sort.ListNode;
import learn.user.User;
import learn.utils.BeanUtil;
import learn.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.Lifecycle;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.channels.AsynchronousByteChannel;

/**
 * @author 吴飞群
 * @createTime 2022/05/22
 */
@Slf4j
@Service
public class TestService{

    private final RedisTemplate<String, Object>  redisTemplate;
    private final RedissonClient redissonClient;
    static int count = 0;

    @Autowired
    public TestService(RedisTemplate<String, Object> redisTemplate, RedissonClient redissonClient) {
        this.redisTemplate = redisTemplate;
        this.redissonClient = redissonClient;
    }


    public void test(String str) {
        System.out.println("execute test method!");
    }

    @PostConstruct
    public void doTask(){
        System.out.println("this is done in post construct!");
    }

    @PreDestroy
    public void doTask_1(){
        System.out.println("this is done in pre destroyed!");
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
        SingleObject singleObject = SingleObject.getInstance();
        singleObject.sayHello();

    }



}
