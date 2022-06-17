package learn.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 吴飞群
 * @createTime 2022/06/16
 */
@Slf4j
@Configuration
public class MeterConfig {

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        log.info("Monitor bean created!");
        return new TimedAspect(registry);
    }
}
