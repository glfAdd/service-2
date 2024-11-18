package com.glf.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glfadd
 */
@RestController
@Api(value = "健康检测", tags = {"健康检测1", "健康检测2"})
public class HealthController {
    @GetMapping("health")
    @ApiOperation(value = "检查1", notes = "检查提示1", tags = {"tag1", "tag2"})
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("health");
    }

    @GetMapping("/health-str")
    @ApiOperation(value = "检查2", notes = "检查提示2", tags = {"tag1", "tag2"})
    public String healthStr() {
        return "health-str";
    }
}
