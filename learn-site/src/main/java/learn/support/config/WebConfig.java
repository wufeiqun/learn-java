package learn.support.config;


import learn.support.interceptor.AdminInterceptor;
import learn.support.interceptor.LoginStatusInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    private final LoginStatusInterceptor loginStatusInterceptor;
    private final AdminInterceptor adminInterceptor;

    @Autowired
    public WebConfig(LoginStatusInterceptor loginStatusInterceptor, AdminInterceptor adminInterceptor) {
        this.loginStatusInterceptor = loginStatusInterceptor;
        this.adminInterceptor = adminInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginStatusInterceptor).addPathPatterns("/api/**");
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");
    }
}
