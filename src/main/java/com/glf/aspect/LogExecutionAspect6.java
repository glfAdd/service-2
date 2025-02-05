package com.glf.aspect;

import com.glf.annotation.LogExecution6;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogExecutionAspect6 {
    private static final Logger logger = LoggerFactory.getLogger(LogExecutionAspect6.class);

    // 使用切点表达式拦截指定包或类下的所有方法
    // @Around("execution(* com.glf.controller.*.*(..)) && !@annotation(com.glf.demo.LogExecution)")
    @Around("@annotation(com.glf.annotation.LogExecution1)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 执行目标方法
        Object proceed = joinPoint.proceed();
        System.out.println("111111111111");
        return proceed;
    }
}
