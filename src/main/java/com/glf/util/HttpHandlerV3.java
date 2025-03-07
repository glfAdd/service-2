package com.glf.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author glfadd
 */
@Slf4j
public class HttpHandlerV3 {

    private final int timeoutSecond;
    private final OkHttpClient client;

    public HttpHandlerV3(int timeoutSecond) {
        this.timeoutSecond = timeoutSecond;
        this.client = createClient();
    }

    public HttpHandlerV3() {
        this.timeoutSecond = -1;
        this.client = createClient();
    }

    private OkHttpClient createClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (timeoutSecond != -1) {
            clientBuilder.connectTimeout(this.timeoutSecond, TimeUnit.SECONDS);
            clientBuilder.readTimeout(this.timeoutSecond, TimeUnit.SECONDS);
            clientBuilder.writeTimeout(this.timeoutSecond, TimeUnit.SECONDS);
        }
        return clientBuilder.build();
    }


    public Response sendGet(String url, Map<String, Object> params, Map<String, Object> headers) throws IOException {
        Request.Builder requestBuilder = new Request.Builder();

        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        if (params != null) {
            params.forEach((k, v) -> httpBuilder.addQueryParameter(k, (String) v));
        }
        requestBuilder.url(httpBuilder.build());

        if (headers != null) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), (String) entry.getValue());
            }
        }
        Request request = requestBuilder.build();
        return this.client.newCall(request).execute();
    }

    public Response sendPost(String url, String body, Map<String, String> headers) throws IOException {
        Request.Builder requestBuilder = new Request.Builder();

        MediaType type = MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.create(body, type);
        requestBuilder.post(requestBody);

        requestBuilder.url(url);

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        Request request = requestBuilder.build();
        return this.client.newCall(request).execute();
    }

    public void sendPut() {
    }

    public void sendDelete() {
    }

    public void sendOptions() {
    }

    public void sendHead() {
    }

    public void sendConnect() {
    }

    public void sendTrace() {
    }

    /**
     * 对象转为字符串
     *
     * @param object 对象
     * @return
     */
    public static String formatObjectToJsonString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 字符串转为对象
     *
     * @param jsonString
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T formatStringToObject(String jsonString, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonString, tClass);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public static void main(String[] args) throws IOException {
        HttpHandlerV3 h = new HttpHandlerV3();
        Response a = h.sendGet("http://localhost:8080/health", null, null);
        System.out.println("1111111111111111111");
    }
}
