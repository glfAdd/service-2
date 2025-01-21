package com.glf.dto.animal.cat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author glfadd
 */
@Data
public class ReqCat2DTO {
    // value: 描述
    // notes: 注释说明
    // name: 覆盖属性名称
    // allowableValues: 长度限制
    // dataType: 数据类型
    // required: 是否必填
    // position: 指定属性顺序
    // hidden: 是否隐藏
    // example: 示例
    // accessMode: 指定模型属性的访问模式（只读或读写）
    // reference: 对相应类型定义的引用，覆盖其他任何指定的元数据
    // allowEmptyValue: 是否允许传递空值
    @ApiModelProperty(
            value = "动物的名字",
            notes = "动物名字的一长段说明文字",
//            name = "name2",
            allowableValues = "Tome, Jack, Luck",
            dataType = "String",
            required = true,
            position = 99,
            hidden = false,
            example = "小明",
            accessMode = ApiModelProperty.AccessMode.READ_ONLY,
            reference = "hello",
            allowEmptyValue = true
    )
    @Size(max = 50, message = "名字最大长度 50")
    private String name;

    @Min(value = 1, message = "年龄最少为 1")
    @Max(value = 30, message = "年龄最大为 30")
    private Integer age;

    @ApiModelProperty(value = "性别", required = true, allowableValues = "0, 1")
    @NotNull(message = "性别必填")
    @NotBlank(message = "性别不能为空")
    private Integer gender;
}
