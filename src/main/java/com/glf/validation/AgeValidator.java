package com.glf.validation;
/*
自定义验证器
*/

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class AgeValidator implements ConstraintValidator<ValidAge, Integer> {

    @Override
    public void initialize(ValidAge constraintAnnotation) {
        // 可以在这里执行一些初始化操作
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 验证逻辑：检查值是否大于 0
        return value != null && value > 0;
    }
}
