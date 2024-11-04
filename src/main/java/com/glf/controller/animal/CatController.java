package com.glf.controller.animal;

import com.glf.dto.animal.cat.ReqCatListDTO;
import com.glf.dto.animal.cat.ResCatListDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@Api(tags = "Cat")
@Validated
public class CatController {
    @ApiImplicitParam(name = "name", value = "姓名", required = true)
    @ApiOperation(value = "向客人问好")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok("Hi:" + name);
    }

    @GetMapping("getCatList")
    @ApiOperation(value = "获取 - cat 列表")
    public ResCatListDTO getCatList(ReqCatListDTO reqCatListDTO) {
        ResCatListDTO res = new ResCatListDTO();
        return res;
    }
}
