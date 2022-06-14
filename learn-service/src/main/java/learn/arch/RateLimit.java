package learn.arch;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author 吴飞群
 * @createTime 2022/06/13
 */
public class RateLimit {

    public void test() {
        RateLimiter limiter = RateLimiter.create(5);
    }
}
