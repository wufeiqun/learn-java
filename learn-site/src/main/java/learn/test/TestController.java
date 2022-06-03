package learn.test;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

/**
 * @author 吴飞群
 * @createTime 2022/05/23
 */
@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private TestService testService;

    @GetMapping()
    public String TestLock() {
        // 1.获取锁，只要锁的名字一样，获取到的锁就是同一把锁。
        RLock lock = redissonClient.getLock("wufeiqun-lock");

        // 2.加锁, 拿锁失败时会不停的重试
        lock.lock();
//        lock.lock(10L, TimeUnit.MILLISECONDS);

        // 尝试拿锁10s后停止重试,返回false
        // 具有Watch Dog 自动延期机制 默认续30s
//        boolean res1 = lock.tryLock(10, TimeUnit.SECONDS);


        //2. 公平锁 保证 Redisson 客户端线程将以其请求的顺序获得锁
//        RLock fairLock = redissonClient.getFairLock("fairLock");
        //3. 读写锁 没错与JDK中ReentrantLock的读写锁效果一样
//        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("readWriteLock");
//        readWriteLock.readLock().lock();
//        readWriteLock.writeLock().lock();
        try {
            System.out.println("加锁成功，执行后续代码。线程 ID：" + Thread.currentThread().getId());
            Thread.sleep(10000);
        } catch (Exception e) {
            //TODO
        } finally {
            lock.unlock();
            // 3.解锁
            System.out.println("Finally，释放锁成功。线程 ID：" + Thread.currentThread().getId());
        }

        HashSet<String> set = new HashSet<>();

        return "test lock ok";
    }

    @GetMapping("/userinfo")
    public String testUserInfo(){
        log.info(testService.toString());
        testService.test3();
        return "OK";
    }

}
