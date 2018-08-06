package com.liumapp.blog.aop.springboot.monitor;

import com.alibaba.fastjson.JSON;
import com.liumapp.blog.aop.springboot.entity.HelloInfo;
import com.liumapp.blog.aop.springboot.entity.HelloTokenInfo;
import com.liumapp.blog.aop.springboot.response.ResponseEntity;
import com.liumapp.blog.aop.springboot.status.Status;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

    @Pointcut("@annotation(com.liumapp.blog.aop.springboot.annotation.ReturnErrorAOP)")
    public void returnError () {}

    @Pointcut("@annotation(com.liumapp.blog.aop.springboot.annotation.CheckToken)")
    public void checkToken () {}


    @AfterReturning("execution(* com.liumapp..*Controller.*(..))")
    public void logServiceResult(JoinPoint joinPoint) {
        System.out.println("Controller Completed: " + joinPoint);
    }

    @Around("returnError()")
    public void returnErrorDetail (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof HttpServletResponse) {
                ((HttpServletResponse) arg).setCharacterEncoding("utf-8");
                ((HttpServletResponse) arg).setContentType("application/json; charset=utf-8");
                try {
                    PrintWriter out = ((HttpServletResponse) arg).getWriter();
                    out.print(JSON.toJSONString(new ResponseEntity("error", Status.ERROR_EXCEPTION)));
                    out.flush();
                    out.close();
                    return ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        proceedingJoinPoint.proceed();
    }

    @Around("checkToken()")
    public void checkTokenDetail (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof HelloTokenInfo) {
                if (((HelloTokenInfo) arg).getToken() != null) {
                    proceedingJoinPoint.proceed();
                }
            }
        }
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
