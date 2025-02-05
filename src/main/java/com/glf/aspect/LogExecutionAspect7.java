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
public class LogExecutionAspect7 {
    private static final Logger logger = LoggerFactory.getLogger(LogExecutionAspect7.class);

    @Pointcut("@annotation(com.glf.annotation.LogExecution1)")
    public void method1() {
    }


    @Before("method1()")
    public void before1(JoinPoint joinPoint) {
        logger.info("before1 {}", joinPoint.getSignature().getName());
    }

    @After("method1()")
    public void after1(JoinPoint joinPoint) {
        logger.info("after1 {}", joinPoint.getSignature().getName());
    }

    /**
     * service 目录下的方法都嗲用这个
     */
    @Pointcut("execution(public * com.glf.controller..*(*))")
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
