package learn.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author 吴飞群
 * @createTime 2022/06/06
 */
@Slf4j
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private  static  ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static ApplicationContext getInstance(){
        return applicationContext;
    }

    /**
     * 使用的时候可以强转, 比如:
     * User user = (User) SpringContextUtil.getBeanByName(user);
     * @param name bean的名称
     */
    public  static  Object getBeanByName(String name){
        if (applicationContext == null){
            log.error("applicationContext is null, beanName={}", name);
            return null;
        }
        return  applicationContext.getBean(name);
    }

    public  static  Object getBeanByClass(Class beanClass){
        if (applicationContext == null){
            log.error("applicationContext is null, beanClass={}", beanClass);
            return null;
        }
        return  applicationContext.getBean(beanClass);
    }

}
