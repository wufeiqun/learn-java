package learn.support.interceptor;

import com.alibaba.fastjson2.JSON;
import learn.constant.Constant;
import learn.pojo.CommonVo;
import learn.user.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

@Component
@Slf4j
public class LoginStatusInterceptor implements HandlerInterceptor {
    private final String tokenHeader = "accessToken";



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        User user = getUserByToken(request);
        User user = new User();
        user.setAccount("wufeiqun");
        user.setName("吴飞群");
        if (Objects.isNull(user)){
            CommonVo<Object> commonVo = CommonVo.error("4001", "当前用户未登陆或token已失效");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            String jsonString = JSON.toJSONString(commonVo);
            writer.print(jsonString);
            writer.close();
            response.flushBuffer();
            return false;
        }
        request.setAttribute(Constant.requestAttributeUserKey, user);
        return true;

    }

    /**
     * 从请求header中拿到用户的token, 然后去用户中心换取用户的基本信息
     */
    private User getUserByToken(HttpServletRequest request) {
        User user = null;
        String token = request.getHeader(tokenHeader);
        if (StringUtils.isNotBlank(token)) {
            //TODO: 调用用户中心服务换取用户的基本信息
            try{
                user = new User();
            } catch(Exception e){
                log.error("根据token换取用户信息异常, 调用用户中心失败", e);
            }
        }
        return user;
    }

}
