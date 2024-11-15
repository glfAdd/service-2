package com.glf.controller;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glfadd
 */
@RestController
@Api(tags = "HealthCheck")
public class HealthController {
    @GetMapping("health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("health");
    }

    @GetMapping("/health-str")
    public String healthStr() {
        return "health-str";
    }
}
