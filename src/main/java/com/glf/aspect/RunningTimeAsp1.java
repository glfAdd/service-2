package com.glf.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author glfadd
 * 无参数, 方式 1
 */
@Aspect
@Component
@Slf4j
public class RunningTimeAsp1 {

    @Around("@annotation(com.glf.annotation.RunningTime1)")
    public Object logExe(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        // 执行目标方法
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info("{} 执行时间 {}ms", joinPoint.getSignature(), executionTime);
        return result;
    }

}
