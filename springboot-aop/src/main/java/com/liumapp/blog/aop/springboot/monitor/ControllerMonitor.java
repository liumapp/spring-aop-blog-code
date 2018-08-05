package com.liumapp.blog.aop.springboot.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file ControllerMonitor.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/7/31
 */
@Component
@Aspect
public class ControllerMonitor {

    @Before("execution(* com.liumapp..*Controller.*(..))")
    public void logServiceBegin (JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("Controller begin: " + joinPoint);
    }

    @AfterReturning("execution(* com.liumapp..*Controller.*(..))")
    public void logServiceResult(JoinPoint joinPoint) {
        System.out.println("Controller Completed: " + joinPoint);
    }

}
