package com.springapp.springbootappwithh2.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.springapp.springbootappwithh2.controller.ProductController.*(..))")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Before Calling method: {}", methodName);
    }

    @After("execution(* com.springapp.springbootappwithh2.controller.ProductController.*(..))")
    public void after() {
        logger.info("After Method Execution Logged");
    }

    @Around("execution(* com.springapp.springbootappwithh2.*.*(..))")
    public void around() {
        logger.info("After Method Execution Logged");
    }

    @AfterReturning(pointcut = "execution(* com.springapp.springbootappwithh2.controller.ProductController.*(..))", returning = "result")
    public void afterProductControllerMethods(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Finished executing method: {} {}", methodName, result);
    }

    @AfterThrowing(pointcut = "execution(* com.springapp.springbootappwithh2.controller.ProductController.*(..))", throwing = "exception")
    public void afterThrowingProductControllerMethods(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("Exception in method {}: {}", methodName, exception.getMessage());
    }
}
