package com.glf.util;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.SendResult;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 处理 http 请求
 *
 * @author glfadd
 */
public class HttpHandlerV2 {
    private static final Logger log = LoggerFactory.getLogger(HttpHandlerV2.class);

    private int timeout;

    public HttpHandlerV2() {
        this.timeout = 10 / 3;

    }

    public HttpHandlerV2(int timeout) {
        this.timeout = timeout / 3;
    }


    private Response sendRequest(String method, String url, Map<String, String> params, String body, Map<String, String> headers) {
        // step 1: 创建 client
        OkHttpClient client = new OkHttpClient.Builder()
                // 设置连接超时时间
                .connectTimeout(this.timeout, TimeUnit.SECONDS)
                // 设置读取超时时间
                .readTimeout(this.timeout, TimeUnit.SECONDS)
                // 设置写入超时时间
                .writeTimeout(this.timeout, TimeUnit.SECONDS).build();

        // step 2: 创建 request
        Request.Builder requestBuilder = new Request.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        if (Objects.equals(method, "get")) {
            // get 请求
            HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
                }
            }
            HttpUrl httpUrl = urlBuilder.build();
            requestBuilder.url(httpUrl);
        } else if (Objects.equals(method, "post")) {
            // post 请求
            requestBuilder.url(url);
            requestBuilder.post(RequestBody.create(body, MediaType.get("application/json; charset=utf-8")));
        }
        Request request = requestBuilder.build();

        // step 3: 发送请求
        try (Response response = client.newCall(request).execute()) {
            return response;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    /**
     * 发送 get 请求
     *
     * @param url     请求 url
     * @param params  请求参数
     * @param headers 请求header
     * @param timeout 请求超时时间
     * @return 状态码和响应体
     */
    public Map<String, Object> get(String url, Map<String, String> params, Map<String, String> headers, Integer timeout) {
        Map<String, Object> res = new HashMap<>();
        Response response = sendRequest("get", url, params, null, headers);
        if (response != null) {
            if (response.isSuccessful()) {
                res.put("code", response.code());
                if (response.body() != null) {
                    res.put("body", response.body().string());
                }
            } else {
                System.err.println("Request failed with response code: " + response.code());
            }
        }
        return res;
    }


    /**
     * 发送 post 请求
     *
     * @param url     请求 url
     * @param body    请求体
     * @param headers 请求 header
     * @param timeout 请求超时时间
     * @return 状态码和响应体
     */
    public static Map<String, Object> post(String url, String body, Map<String, String> headers, Integer timeout) {
        Map<String, Object> res = new HashMap<>();
        OkHttpClient client = createClient(timeout);
        Request request = createRequest("post", url, null, body, headers);
        // 发送请求
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                res.put("code", response.code());
                if (response.body() != null) {
                    res.put("body", response.body().string());
                }
            } else {
                System.err.println("Request failed with response code: " + response.code());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return res;
    }

    public void put() {

    }

    public void delete() {

    }

    public void options() {

    }

    public void head() {

    }

    public void connect() {

    }

    public void trace() {

    }

    public static void main(String[] args) {
        Map<String, Object> a = get("http://localhost:8080/health", null, null, null);
        String b = a.get("body").toString();
        System.out.println(b);
    }
}
