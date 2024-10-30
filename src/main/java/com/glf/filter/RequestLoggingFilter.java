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

/**
 * @author glfadd 过滤器, 打印每个请求的 log
 */
@Component
public class RequestLoggingFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String requestId = UUID.randomUUID().toString();
        httpRequest.setAttribute("X-RequestId", requestId);
        httpResponse.setHeader("X-RequestId", requestId);
        if ("POST".equalsIgnoreCase(httpRequest.getMethod()) && "application/json".equals(httpRequest.getContentType())) {
            // 读取 JSON 请求体
            StringBuilder jsonBody = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpRequest.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
            logger.info("Request Method: {}, Request URI: {}, JSON Request Body: {}", httpRequest.getMethod(), httpRequest.getRequestURI(), jsonBody);
        } else if ("POST".equalsIgnoreCase(httpRequest.getMethod())) {
            StringBuilder body = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpRequest.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
            // 打印表单参数
            logger.info("Form Parameters: {}", body);
        }

        //        logger.info("Request Method: {}, Request URI: {}, Request ", httpRequest.getMethod());
        //        logger.info("Request Method: {}", httpRequest.getMethod());
        //        logger.info("Request URI: {}", httpRequest.getRequestURI());
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            logger.info("{}: {}", headerName, httpRequest.getHeader(headerName));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
