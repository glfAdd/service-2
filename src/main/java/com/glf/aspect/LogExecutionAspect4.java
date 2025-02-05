package com.glf.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author glfadd
 * 无参数, 方式 2
 */
@Aspect
@Component
@Slf4j
public class LogExecutionAspect4 {
    private static final Logger logger = LoggerFactory.getLogger(LogExecutionAspect4.class);

    @Pointcut("execution(public * com.glf.controller.*.*.*(..))")
    // @Pointcut("execution(public * com.glf.controller.animal.CatController.*(..))")
    public void method2() {
    }

    @Before("method2()")
    public void before2(JoinPoint joinPoint) {
        logger.info("before2 {}", joinPoint.getSignature().getName());
    }

    @After("method2()")
    public void after2(JoinPoint joinPoint) {
        logger.info("after2 {}", joinPoint.getSignature().getName());
    }
}
