// package com.glf.aspect;
//
// import com.glf.annotation.LogExecution9;
// import lombok.extern.slf4j.Slf4j;
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Pointcut;
// import org.springframework.stereotype.Component;
//
// /**
//  * @author glfadd
//  * 有参数
//  */
// // 表明这是一个切面类
// @Aspect
// @Component
// @Slf4j
// public class LogExecutionAspect9 {
//
//     @Pointcut("@annotation(com.glf.annotation.LogExecution9)")
//     public void logPointCut() {
//         System.out.println("logPointCut");
//     }
//
//
//     // public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//     @Around("logPointCut()")
//     public Object logExecutionTime(ProceedingJoinPoint joinPoint, LogExecution9 logExecution9) throws Throwable {
//         long start = System.currentTimeMillis();
//         // 执行目标方法
//         Object proceed = joinPoint.proceed();
//         long executionTime = System.currentTimeMillis() - start;
//
//         // 根据 logLevel 来决定日志等级
//         switch (logExecution9.logLevel()) {
//             case "DEBUG":
//                 log.debug(logExecution9.message() + " - executed in " + executionTime + "ms");
//                 break;
//             case "WARN":
//                 log.warn(logExecution9.message() + " - executed in " + executionTime + "ms");
//                 break;
//             case "ERROR":
//                 log.error(logExecution9.message() + " - executed in " + executionTime + "ms");
//                 break;
//             default:
//                 log.info(logExecution9.message() + " - executed in " + executionTime + "ms");
//                 break;
//         }
//         return proceed;
//     }
//
// }
