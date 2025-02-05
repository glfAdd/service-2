package com.glf.controller.animal;

import com.glf.annotation.LogExecution2;
import com.glf.annotation.LogExecution1;
import com.glf.dto.animal.cat.ReqCat2DTO;
import com.glf.dto.animal.cat.ResCat2DTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

/**
 * @author glfadd
 */
@RestController
@RequestMapping("cat")
@Api(tags = "接收请求解析 - CatController")
// 启动参数校验
@Validated
public class CatController {
    @LogExecution1
    @ApiOperation(value = "url 参数 (不使用 swagger)")
    @GetMapping("/request1")
    public ResponseEntity<String> request1(@RequestParam(value = "name") String name, @RequestParam(value = "age") Integer age) {
        return ResponseEntity.ok(String.format("%s - %s", name, age));
    }

    @LogExecution2(logLevel = "DEBUG", message = "查询接口2")
    @ApiOperation(value = "url 参数 (使用 swagger)")
    @GetMapping("/request2")
    public ResponseEntity<String> request2(
            @ApiParam(name = "name", value = "用户名", example = "Luck") @RequestParam(value = "name") String name,
            @ApiParam(name = "age", required = true, value = "年龄", example = "1") @RequestParam(value = "age") Integer age) {
        return ResponseEntity.ok(String.format("%s - %s", name, age));
    }

    @ApiOperation(value = "url 参数 (使用 swagger 结合 Valid)")
    @GetMapping("/request3")
    public ResponseEntity<String> request3(
            @ApiParam(name = "name", value = "用户名", required = false, example = "Tom") @RequestParam(value = "name", required = false) @Length(max = 10, message = "name 最大长度 10") String name,
            @ApiParam(name = "age", value = "年龄", required = true, example = "18") @RequestParam(value = "age", required = true) @Min(value = 1, message = "age 必须大于 1") Integer age) {
        return ResponseEntity.ok(String.format("%s - %s", name, age));
    }

    @ApiOperation(value = "url 参数 (数组传参)")
    @GetMapping("/request4")
    public ResponseEntity<String> request4(
            @ApiParam(name = "age", value = "年龄", required = true, example = "22,23") @RequestParam(name = "age", required = false) Integer[] age) {
        for (Integer integer : age) {
            System.out.println(integer);
        }
        return ResponseEntity.ok("123");
    }

    @ApiOperation(value = "url 参数 (对象解析)")
    @GetMapping("request5")
    public ResponseEntity<String> request5(@Validated ReqCat2DTO reqCat2DTO) {
        ResCat2DTO res = new ResCat2DTO();
        return ResponseEntity.ok("123");
    }

    // 正则表达式参数

    // @PathVariable用于路径参数
    // @RequestHeader 解析请求头中的参数


//    @GetMapping("/api/byGetQueryString")
//    public String byGetQueryString(HttpServletRequest request) {
//        return request.getQueryString();
//    }
}
