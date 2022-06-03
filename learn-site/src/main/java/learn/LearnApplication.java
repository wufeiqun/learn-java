package learn;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@ComponentScan(value = {"learn"})
@MapperScan(value = {"learn"})
@Slf4j
@SpringBootApplication
@EnableTransactionManagement
//@ImportResource({"classpath:spring-dubbo-consumer.xml", "classpath:spring-dubbo-provider.xml", "classpath:spring-esjob-config.xml"})
public class LearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnApplication.class, args);
        log.info("project learn Start Success!");
    }

}
