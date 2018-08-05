package com.liumapp.blog.aop.springboot.monitor;

import com.alibaba.fastjson.JSON;
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

    @Before("execution(* com.liumapp..*Controller.index(..))")
    public void allControllerMethod (JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        args[0] += "_updated";
        args[1] += "_updated";
        System.out.println("Controller begin: " + JSON.toJSONString(args));
    }

    @AfterReturning("execution(* com.liumapp..*Controller.*(..))")
    public void logServiceResult(JoinPoint joinPoint) {
        System.out.println("Controller Completed: " + joinPoint);
    }

}
