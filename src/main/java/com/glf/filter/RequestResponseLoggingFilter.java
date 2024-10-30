package com.glf.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.UUID;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import java.util.UUID;

@Component
public class RequestResponseLoggingFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestId = UUID.randomUUID().toString();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // 打印请求信息
        logRequestInfo(httpRequest, requestId);

        // 继续执行过滤器链
        CustomResponseWrapper responseWrapper = new CustomResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, responseWrapper);

        // 打印响应信息
        logResponseInfo(responseWrapper);
    }

    private void logRequestInfo(HttpServletRequest request, String requestId) throws IOException {
        request.setAttribute("X-RequestId", requestId);
        if ("POST".equalsIgnoreCase(request.getMethod()) && "application/json".equals(request.getContentType())) {
            InputStreamReader isr = new InputStreamReader(request.getInputStream());
            Object j = new Gson().fromJson(isr, Object.class);
            logger.info("RequestId: {}, RequestMethod: {}, RequestURI: {}, JSONRequestBody: {}", requestId, request.getMethod(), request.getRequestURI(), new Gson().toJson(j));
        } else {
            logger.info("RequestId: {}, RequestMethod: {}, RequestURI: {}", requestId, request.getMethod(), request.getRequestURI());
        }
    }


    private void logResponseInfo(CustomResponseWrapper response) throws IOException {
        String responseBody = response.getCapturedResponseBody();
        String contentType = response.getContentType();
        if (contentType != null) {
            logger.info("Content-Type: {}", contentType);
            if (contentType.contains("application/json")) {
                logger.info("JSON Response Body: {}", responseBody);
            } else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
                logger.info("Text Response Body: {}", responseBody);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化代码（如果需要）
    }

    @Override
    public void destroy() {
        // 清理代码（如果需要）
    }
}
