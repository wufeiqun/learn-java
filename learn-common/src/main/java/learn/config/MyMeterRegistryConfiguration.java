package learn.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MyMeterRegistryConfiguration {

    /**
     * 可以采用这种方式灵活地修改MeterRegistry, 比如:
     * 1. 添加自定义的全局tags
     * 2. 修改监控数据的暴露策略, 防止暴露出大量无用的监控数据
     */
    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
//        return (registry) -> registry.config().commonTags("ccc", "ddd").meterFilter(MeterFilter.denyNameStartsWith("jvm"));
        return (registry) -> registry.config().commonTags("ccc", "ddd");
    }

}
