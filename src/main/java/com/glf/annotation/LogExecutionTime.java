package com.glf.annotation;

import java.lang.annotation.*;

/**
 * 方法运行时间 (没有参数)
 *
 * @author glfadd
 */
// 作用于方法
@Target(ElementType.METHOD)
// 在运行时可用
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogExecutionTime {

}
