package com.glf.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author glfadd
 */
@Configuration
public class HttpHandler {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
