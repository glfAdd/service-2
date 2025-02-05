package com.glf.annotation;

import java.lang.annotation.*;

/**
 * @author glfadd
 * 无参数
 */
// 作用于方法
@Target(ElementType.METHOD)
// 在运行时可用
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RunningTime1 {

}
