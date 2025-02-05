package com.glf.aspect;

import com.glf.annotation.LogExecution2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author glfadd
 */
@Aspect
@Component
public class LogExecutionAspect2 {
    private static final Logger logger = LoggerFactory.getLogger(LogExecutionAspect2.class);

    // 这里的 logExe3 是下面的参数
    @Around("@annotation(logExe3)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, LogExecution2 logExe3) throws Throwable {
        // 获取注解中的 logLevel 和 message 参数
        String logLevel = logExe3.logLevel();
        String message = logExe3.message();
        // 记录日志，基于注解中的 logLevel 来决定日志级别
        long start = System.currentTimeMillis();
        // 执行目标方法
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        // 根据注解的 logLevel 值来决定日志输出的级别
        switch (logLevel.toUpperCase()) {
            case "DEBUG":
                logger.info("{} {} 执行时间 {}ms", joinPoint.getSignature(), message, executionTime);
                break;
            case "WARN":
                logger.warn("{} {} 执行时间 {}ms !", joinPoint.getSignature(), message, executionTime);
                break;
            case "ERROR":
                logger.error("{} {} 执行时间 {}ms !!", joinPoint.getSignature(), message, executionTime);
                break;
            case "INFO":
            default:
                logger.info("{} {} 执行时间 {}ms", joinPoint.getSignature(), message, executionTime);
                break;
        }
        return proceed;
    }


}
