package com.liumapp.blog.aop.springboot.monitor;

import com.alibaba.fastjson.JSON;
import com.liumapp.blog.aop.springboot.entity.HelloInfo;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
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

    @Pointcut("@annotation(com.liumapp.blog.aop.springboot.annotation.RequireAOP)")
    public void handleComponent () {}

    @AfterReturning("execution(* com.liumapp..*Controller.*(..))")
    public void logServiceResult(JoinPoint joinPoint) {
        System.out.println("Controller Completed: " + joinPoint);
    }

    @Before("handleComponent()")
    public void handleComponentDetail (JoinPoint joinpoint) {
        Object[] args = joinpoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof HelloInfo) {
                ((HelloInfo) arg).setName("lisi");
                ((HelloInfo) arg).setSex("boy");
            }
        }
    }

}
