package com.glf.dto.animal.cat;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author glfadd
 */
@Data
public class ResCatListDTO {
    @Size(min = 0, max = 50, message = "名字最大长度 50")
    private String name;
    @Min(value = 1, message = "年龄最少为 1")
    @Max(value = 30, message = "年龄最大为 30")
    private Integer age;
    private String gender;
}
