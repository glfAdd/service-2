package com.glf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author glfadd
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecution2 {
    // 日志级别，默认是 INFO
    String logLevel() default "INFO";

    // 自定义日志消息，默认是 "Executing method"
    String message() default "Executing method";
}
