package com.glf.controller.animal;

import com.glf.dto.BaseResponse;
import com.glf.dto.animal.dog.ReqDog1DTO;
import com.glf.dto.animal.dog.ResDog1DTO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author glfadd
 */
@RestController
@RequestMapping("dog")
@Api(tags = "请求参数校验")
public class DogController {

    @GetMapping("request1")
    public BaseResponse<ResDog1DTO> getDog(@Valid @RequestBody ReqDog1DTO reqDog1DTO) {
        ResDog1DTO resDog1DTO = new ResDog1DTO();
        return BaseResponse.success(resDog1DTO);
    }
}
