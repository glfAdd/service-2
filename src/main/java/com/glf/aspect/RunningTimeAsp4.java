package com.glf.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author glfadd
 * 无参数, 方式 2
 */
@Aspect
@Component
@Slf4j
public class RunningTimeAsp4 {
    private static final Logger logger = LoggerFactory.getLogger(RunningTimeAsp4.class);

    @Pointcut("execution(public * com.glf.controller.*.*.*(..))")
    // @Pointcut("execution(public * com.glf.controller.animal.CatController.*(..))")
    public void logExe() {
    }

    @Before("logExe()")
    public void before2(JoinPoint joinPoint) {
        logger.info("before2 {}", joinPoint.getSignature().getName());
    }

    @After("logExe()")
    public void after2(JoinPoint joinPoint) {
        logger.info("after2 {}", joinPoint.getSignature().getName());
    }
}
