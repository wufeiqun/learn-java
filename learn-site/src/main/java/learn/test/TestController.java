package learn.test;

import io.micrometer.core.annotation.Timed;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import learn.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author 吴飞群
 * @createTime 2022/05/23
 */
@Slf4j
@Timed(extraTags = { "region", "us-east-1" })
@RestController
@RequestMapping("/api/test")
public class TestController {
    private final RedissonClient redissonClient;
    private final TestService testService;
    @Autowired
    public TestController(RedissonClient redissonClient, TestService testService) {
        this.redissonClient = redissonClient;
        this.testService = testService;
    }

    @GetMapping()
    public String test() {
        return "OK";
    }

    @GetMapping("/userinfo")
    public String testUserInfo(){
//        ApplicationContext context = SpringContextUtil.getInstance();
        Object obj = SpringContextUtil.getBeanByName("timedAspect");
//        testService.test();
        log.info("收到请求!");
        return "OK";
    }

}
