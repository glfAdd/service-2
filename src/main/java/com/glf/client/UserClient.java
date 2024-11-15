package com.glf.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author glfadd
 */
@FeignClient(value = "UserClient", url = "http://127.0.0.1:8080")
public interface UserClient {
    @GetMapping("/health")
    String requestTest1();
}
