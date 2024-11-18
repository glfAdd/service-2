package com.glf.controller.animal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
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
public class CatController {
    @ApiOperation(value = "url 参数 (不使用 swagger)")
    @GetMapping("/request1")
    public ResponseEntity<String> request1(@RequestParam(value = "name") String name, @RequestParam(value = "age") Integer age) {
        return ResponseEntity.ok(String.format("%s - %s", name, age));
    }

    @ApiOperation(value = "url 参数 (使用 swagger)")
    @GetMapping("/request2")
    public ResponseEntity<String> request2(
            @ApiParam(name = "name", value = "用户名", defaultValue = "Tom", example = "Luck") @RequestParam(value = "name") String name,
            @ApiParam(name = "age", required = true, value = "年龄", defaultValue = "18", example = "1") @RequestParam(value = "age") Integer age) {
        return ResponseEntity.ok(String.format("%s - %s", name, age));
    }

    @ApiOperation(value = "url 参数 (使用 swagger 结合 Valid)")
    @GetMapping("/request3")
    public ResponseEntity<String> request3(
            @ApiParam(name = "name", value = "用户名", required = false, defaultValue = "Tom", example = "Luck") @RequestParam(value = "name", required = false) @Length(max = 10, message = "name 最大长度 10") String name,
            @ApiParam(name = "age", value = "年龄", required = true, defaultValue = "18", example = "1") @RequestParam(value = "age", required = true) @Min(value = 1, message = "age 必须大于 1") Integer age) {
        return ResponseEntity.ok(String.format("%s - %s", name, age));
    }
//    @ApiOperation(value = "url 参数 (对象解析)")
//    @GetMapping("request3")
//    public BaseResponse<ResCat2DTO> request3(@Valid ReqCat2DTO reqCatListDTO) {
//        ResCat2DTO res = new ResCat2DTO();
//        return BaseResponse.success(res);
//    }
}
