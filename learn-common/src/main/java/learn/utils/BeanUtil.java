package learn.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

/**
 * @author 吴飞群
 * @createTime 2022/05/26
 */
public class BeanUtil {
    public static <T> T deepCopy(final Object src, Class<T> des) {
        String jsonString = JSON.toJSONString(src);
        return JSONObject.parseObject(jsonString, des);
    }
}
