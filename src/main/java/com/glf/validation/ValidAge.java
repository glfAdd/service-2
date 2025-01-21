package com.glf.validation;

/*
自定义注解
*/

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 *
 * @author glfadd
 */ // 指定验证逻辑类
@Constraint(validatedBy = AgeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidAge {
    // 默认错误消息
    String message() default "Age must be greater than 0";

    // 分组
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};
}
