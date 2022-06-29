package learn.utils;

import com.alibaba.fastjson2.JSON;
import learn.exception.HttpRequestException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * defender.support.request.support
 * Created by skeeter on 2020/3/2.
 */
@Service
@Slf4j
public class HttpRequestUtil {
    private final OkHttpClient okHttpClient;

    @Autowired
    public HttpRequestUtil(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }


    public <T> T postBody(String url, Object body, RequestHandler<T> handler) {
        return postBody(url, body, null, handler);
    }

    public <T> T postBody(String url, Object body, Map<String, String> headers, RequestHandler<T> handler) {
        RequestBody requestBody = RequestBody.create(JSON.toJSONString(body), MediaType.parse("application/json; charset=utf-8"));
        Request.Builder builder = new Request.Builder().url(url);
        addHeader(builder, headers);
        Request request = builder.post(requestBody).build();
        return execute(request, handler, JSON.toJSONString(body));
    }

    public <T> T putBody(String url, Object body, RequestHandler<T> handler) {
        return putBody(url, body, null, handler);
    }

    public <T> T putBody(String url, Object body, Map<String, String> headers, RequestHandler<T> handler) {
        RequestBody requestBody = RequestBody.create(JSON.toJSONString(body), MediaType.parse("application/json; charset=utf-8"));
        Request.Builder builder = new Request.Builder().url(url);
        addHeader(builder, headers);
        Request request = builder.put(requestBody).build();
        return execute(request, handler, JSON.toJSONString(body));
    }

    public <T> T get(String url,RequestHandler<T> handler) {
        return get(url,null,handler);
    }

    public <T> T get(String url, Map<String, Object> params,  RequestHandler<T> handler) {
        return get(url,params,null,handler);
    }

    public <T> T get(String url, Map<String, Object> params, Map<String, String> headers, RequestHandler<T> handler) {
        String finalUrl = buildGetUrl(url, params);
        Request.Builder builder = new Request.Builder()
                .url(finalUrl);
        addHeader(builder, headers);
        Request request = builder.get().build();
        return execute(request,handler, JSON.toJSONString(params));
    }

    private <T> T execute(Request request, RequestHandler<T> handler, String params) {
        Long start = System.currentTimeMillis();
        try (Response response = okHttpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) {

                log.error("执行HTTP请求出错, URL: [{}], 方法: [{}], 请求参数: [{}], HTTP响应CODE: [{}],  返回: [{}]",request.url(),request.method(), params, response.code(), response.body().string());
                HttpRequestException httpRequestException = new HttpRequestException(response.code(), response.message());
                return handler.onError(httpRequestException);
            }
            String jsonData = response.body().string();
            T convertData = handler.convertData(jsonData);
            Long end = System.currentTimeMillis();
            log.info("执行HTTP请求完毕, URL: [{}], 方法: [{}], 请求参数: [{}],  反序列化后返回: [{}], 耗时: [{}毫秒]",request.url(),request.method(), params, jsonData, end-start);
            return handler.onSuccess(convertData);
        } catch (Exception e) {
            log.error("执行HTTP请求出错, URL: [{}], 方法: [{}], 请求参数: [{}],  异常信息: [{}]",request.url(), request.method(), params, e);
            return handler.onError(e);
        }
    }

    private String buildGetUrl(String url, Map<String, Object> params) {
        if (params == null) {
            return url;
        }
        StringBuilder builder = new StringBuilder(url);
        for (Map.Entry<String, Object> paramEntry : params.entrySet()) {
            if (builder.indexOf("?") < 0) {
                builder.append("?").append(paramEntry.getKey());
            } else {
                builder.append("&").append(paramEntry.getKey());
            }
            builder.append("=").append(paramEntry.getValue());
        }
        return builder.toString();
    }

    private void addHeader(Request.Builder builder, Map<String, String> headers) {
        if (headers != null) {
            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                builder.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
    }

}
