package learn.test;

import learn.LearnApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = LearnApplication.class)
//@ImportResource({"classpath:spring-dubbo-consumer.xml"})
@WebAppConfiguration
//选择profile
@ActiveProfiles("local")
//@TestPropertySource({"classpath:sentinel.properties","classpath:mryx-common-config.properties","as-frame.properties"})
public class TestBase { }
