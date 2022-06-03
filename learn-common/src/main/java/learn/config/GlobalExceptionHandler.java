package learn.config;


import learn.exception.ServiceException;
import learn.pojo.CommonVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务自定义异常
     */
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public CommonVo<Object> handleServiceException(ServiceException e) {
        log.error("ServiceException=", e);
        return CommonVo.error(String.valueOf(e.getCode()), e.getMessage());
    }

    /**
     * 处理其它异常
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        log.error("exception=", e);
        return CommonVo.error("5000", "未知系统异常");
    }
}
