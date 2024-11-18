package com.glf.controller.animal;

import com.glf.dto.BaseResponse;
import com.glf.dto.animal.cat.ReqCat2DTO;
import com.glf.dto.animal.cat.ResCat2DTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author glfadd
 */
@RestController
@RequestMapping("cat")
@Api(tags = "接收请求解析")
public class CatController {
    @ApiImplicitParam(name = "name", value = "姓名", required = true)
    @ApiOperation(value = "关键字参数")
    @GetMapping("/request1")
    public ResponseEntity<String> request1(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok("Hi:" + name);
    }

    @GetMapping("request2")
    @ApiOperation(value = "关键字参数-对象解析")
    public BaseResponse<ResCat2DTO> request2(@Valid ReqCat2DTO reqCatListDTO) {
        ResCat2DTO res = new ResCat2DTO();
        return BaseResponse.success(res);
    }
}
