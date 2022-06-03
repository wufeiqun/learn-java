package learn.support.interceptor;

import cn.hutool.core.util.IdUtil;
import learn.constant.MdcConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 吴飞群
 * @createTime 2022/05/23
 */
@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) {
        try {
            MDC.remove(MdcConstant.SessionId);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        try {
            // 设置SessionId, 可以改成公司内部的全局的traceID
            String requestId = IdUtil.simpleUUID();
            MDC.put(MdcConstant.SessionId, requestId);
            return true;
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return false;
        }
    }
}



