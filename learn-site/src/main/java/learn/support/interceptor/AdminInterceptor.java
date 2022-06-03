package learn.support.interceptor;

import com.alibaba.fastjson2.JSON;
import learn.pojo.CommonVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

@Component
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
    private final String adminHeaderKey = "X-AdminKey";
    private final String adminHeaderValue = "123456";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean ret = validAdminKey(request);

        if (!ret){
            CommonVo<Object> commonVo = CommonVo.error("4001", "密码不正确");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            String jsonString = JSON.toJSONString(commonVo);
            writer.print(jsonString);
            writer.close();
            response.flushBuffer();
        }

        return ret;

    }

    private boolean validAdminKey(HttpServletRequest request) {
        String token = request.getHeader(adminHeaderKey);
        return Objects.equals(adminHeaderValue, token);
    }

}
