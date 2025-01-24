package com.glf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author glfadd
 */
@SpringBootApplication
@EnableFeignClients
// @EnableAspectJAutoProxy
public class Application1 {
    public static void main(String[] args) {
        SpringApplication.run(Application1.class, args);
    }
}
