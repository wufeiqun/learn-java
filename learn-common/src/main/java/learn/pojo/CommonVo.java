package learn.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonVo<T> implements Serializable {
    private static final long serialVersionUID = 7564775801965409910L;
    private static String SUCCESS_CODE = "0";
    private static String DEFAULT_ERROR_CODE = "5000";
    /**
     * 0成功 非0具体错误原因
     */
    private String code;
    /**
     * 具体错误描述or成功描述
     */
    private String message = "操作成功";
    /**
     * 存放业务数据
     */
    private T data;

    public static <T> CommonVo<T> success() {
        CommonVo<T> ret = new CommonVo<T>();
        ret.setCode(SUCCESS_CODE);
        return ret;
    }

    public static <T> CommonVo<T> success(T data) {
        CommonVo<T> ret = new CommonVo<T>();
        ret.setData(data);
        ret.setCode(SUCCESS_CODE);
        return ret;
    }

    public static <T> CommonVo<T> success(T data, String message) {
        CommonVo<T> ret = new CommonVo<T>();
        ret.setData(data);
        ret.setCode(SUCCESS_CODE);
        ret.setMessage(message);
        return ret;
    }

    public static <T> CommonVo<T> error(String message) {
        CommonVo<T> ret = new CommonVo<>();
        ret.setCode(DEFAULT_ERROR_CODE);
        ret.setMessage(message);
        return ret;
    }

    public static <T> CommonVo<T> error(String code, String message) {
        CommonVo<T> ret = new CommonVo<T>();
        ret.setCode(code);
        ret.setMessage(message);
        return ret;
    }

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }
}
