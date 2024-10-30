package com.glf.controller.animal;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dog")
@Api(tags = "Dog")
public class DogController {

    @GetMapping("get")
    public void getDog() {
    }
}
