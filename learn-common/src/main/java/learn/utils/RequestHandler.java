package learn.utils;

import com.alibaba.fastjson2.JSON;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * brain.app.server.dilatation
 * Created by skeeter on 2019/6/20.
 */
public interface RequestHandler<T> {
    default T onError(Exception e){
        return null;
    }

    default T onSuccess(T data){
        return data;
    }
    @SuppressWarnings(value = "unchecked")
    default T convertData(String jsonData) {
        T parseObject;
        Type[] interfaces = this.getClass().getGenericInterfaces();
        Type anInterface = interfaces[0];
        if (anInterface instanceof Class) {
            //没有暴露泛型,直接传出去jsonObject
            parseObject = (T) JSON.parseObject(jsonData);
        } else {
            Type[] params = ((ParameterizedType) anInterface).getActualTypeArguments();
            parseObject = JSON.parseObject(jsonData, params[0]);
        }
        return parseObject;
    }
}
