package com.glf.util;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final int timeout;

    public HttpHandlerV2() {
        this.timeout = 60 / 3;

    }

    public HttpHandlerV2(int timeout) {
        this.timeout = timeout / 3;
    }


    /**
     * 发送请求
     *
     * @param method  请求方式 (get ,post)
     * @param url     地址
     * @param params  参数
     * @param body    请求体
     * @param headers header
     * @return 响应对象
     */
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
            RequestBody b = RequestBody.create(body, MediaType.get("application/json; charset=utf-8"));
            requestBuilder.url(url);
            requestBuilder.post(b);

        } else if (Objects.equals(method, "put")) {
            // put 请求
            RequestBody b = RequestBody.create(body, MediaType.get("application/json; charset=utf-8"));
            requestBuilder.url(url);
            requestBuilder.put(b);

        } else if (Objects.equals(method, "delete")) {
            // delete 请求

        } else if (Objects.equals(method, "options")) {
            // options 请求

        } else if (Objects.equals(method, "head")) {
            // head 请求

        } else if (Objects.equals(method, "connect")) {
            // connect 请求

        } else if (Objects.equals(method, "trace")) {
            // trace 请求
        }
        Request request = requestBuilder.build();

        // step 3: 发送请求
        try {
            return client.newCall(request).execute();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 响应解析为文本类型
     *
     * @param response 响应
     * @return 状态码和响应体
     */
    private Map<String, Object> formatToText(Response response) {
        Map<String, Object> res = new HashMap<>();
        if (response != null) {
            if (response.isSuccessful()) {
                res.put("code", response.code());
                if (response.body() != null) {
                    try {
                        res.put("body", response.body().string());
                    } catch (IOException e) {
                        log.error(e.getMessage());
                        res.put("body", "");
                    }
                }
            } else {
                System.err.println("Request failed with response code: " + response.code());
            }
        }
        return res;
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
        Response response = sendRequest("get", url, params, null, headers);
        return formatToText(response);
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
    public Map<String, Object> post(String url, String body, Map<String, String> headers, Integer timeout) throws IOException {
        Response response = sendRequest("post", url, null, body, headers);
        return formatToText(response);
    }

    public Map<String, Object> put(String url, String body, Map<String, String> headers, Integer timeout) throws IOException {
        Response response = sendRequest("put", url, null, body, headers);
        return formatToText(response);
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
        HttpHandlerV2 h = new HttpHandlerV2(30);
        Map<String, Object> a = h.get("http://localhost:8080/health", null, null, null);
        System.out.println(a);
    }
}
