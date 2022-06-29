package learn.test;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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

//    @Timed("custom-service-name")
//    @Timed
    public String test() {
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("execute test method!");
        return "Hello Sleep!";
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
