package com.glf.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogExecutionAspect3 {
    private static final Logger logger = LoggerFactory.getLogger(LogExecutionAspect3.class);

    @Pointcut("@annotation(com.glf.annotation.LogExecution3)")
    public void logPointCut6() {
    }

    // 使用切点表达式拦截指定包或类下的所有方法
    // @Around("execution(* com.glf.controller.*.*(..)) && !@annotation(com.glf.demo.LogExecution)")
    @Around(" logPointCut6()")
    public Object logExe(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("开始执行 {}", joinPoint.getSignature().getName());
        Object proceed = joinPoint.proceed();
        logger.info("执行完成 {}", joinPoint.getSignature().getName());
        return proceed;
    }

    @Before("logPointCut6()")
    public void before(JoinPoint joinPoint) {
        logger.info("{} 执行 before", joinPoint.getSignature().getName());
    }

    @After("logPointCut6()")
    public void after(JoinPoint joinPoint) {
        logger.info("{} 执行 after", joinPoint.getSignature().getName());
    }


}
