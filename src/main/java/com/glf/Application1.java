package com.glf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author glfadd
 */
@SpringBootApplication
@EnableFeignClients
public class Application1 {
    public static void main(String[] args) {
        SpringApplication.run(Application1.class, args);
    }
}
