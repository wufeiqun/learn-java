package learn.test;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.prometheus.client.Collector;
import learn.utils.AsyncUtil;
import learn.utils.HttpRequestUtil;
import learn.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author 吴飞群
 * @createTime 2022/05/23
 */
@Slf4j
@Timed(extraTags = { "cloud", "aliyun" }, histogram = true)
@RestController
@RequestMapping("/api/test")
public class TestController {
    private final RedissonClient redissonClient;
    private final TestService testService;

    private final AsyncUtil asyncUtil;

    private final MeterRegistry meterRegistry;

    private final HttpRequestUtil httpRequestUtil;

    @Autowired
    public TestController(RedissonClient redissonClient, TestService testService, AsyncUtil asyncUtil, MeterRegistry meterRegistry, HttpRequestUtil httpRequestUtil) {
        this.redissonClient = redissonClient;
        this.testService = testService;
        this.asyncUtil = asyncUtil;
        this.meterRegistry = meterRegistry;
        this.httpRequestUtil = httpRequestUtil;
    }

    @GetMapping()
    public String test() {
        return "OK";
    }

    @GetMapping("/userinfo")
    public String testUserInfo(){
//        ApplicationContext context = SpringContextUtil.getInstance();
        Object obj = SpringContextUtil.getBeanByName("redissonClient");
//        Object obj1 = SpringContextUtil.getBeanByClass(TimedAspect.class);
        log.info("收到请求!");
        asyncUtil.doTask(() ->{
            log.info("do in the async thread!");
        });
        return "OK";
    }

    @GetMapping("/timer")
    public String testTimer() {
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sample.stop(meterRegistry.timer("custom.test.timer", "test-key", "test-value"));

        return "OK";
    }

    @GetMapping("/summary")
    public String testDistributionSummary(){
        DistributionSummary summary = DistributionSummary
                .builder("response.size")
                .description("a description of what this summary does") // optional
                .baseUnit("bytes") // optional (1)
                .tags("testName", "DistributionSummary") // optional
                .serviceLevelObjectives(0.7, 0.8, 0.9,1,2,3) // optional (2)
                .register(meterRegistry);

        Random random = new Random();
        float r = random.nextFloat();
        log.info(Float.toString(r));
        summary.record(r);
//        redissonClient.getBuckets();
        return "OK";
    }


}
