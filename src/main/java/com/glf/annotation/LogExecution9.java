package com.glf.annotation;

import java.lang.annotation.*;

/**
 * @author glfadd
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogExecution9 {
    // 日志等级，默认 INFO
    String logLevel() default "INFO";

    // 自定义描述信息
    String message() default "Executing method";
}
