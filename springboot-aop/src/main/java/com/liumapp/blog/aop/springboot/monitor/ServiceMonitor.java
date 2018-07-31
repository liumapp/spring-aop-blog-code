package com.liumapp.blog.aop.springboot.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file ServiceMonitor.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/7/31
 */
@Component
@Aspect
public class ServiceMonitor {

    @Before("execution(* com.liumapp..*Service.*(..))")
    public void logServiceBegin (JoinPoint joinPoint) {
        System.out.println("begin: " + joinPoint);
    }

    @AfterReturning("execution(* com.liumapp..*Service.*(..))")
    public void logServiceResult(JoinPoint joinPoint) {
        System.out.println("Completed: " + joinPoint);
    }

}
