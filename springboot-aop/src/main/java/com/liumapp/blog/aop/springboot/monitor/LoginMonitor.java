package com.liumapp.blog.aop.springboot.monitor;

import com.liumapp.blog.aop.springboot.response.ResponseEntity;
import com.liumapp.blog.aop.springboot.status.Status;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author liumapp
 * @file LoginMonitor.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/8/3
 */
@Aspect
@Component
public class LoginMonitor {

    /**
     * 定义拦截规则：拦截标有com.liumapp.blog.aop.springboot.annotation.Login注解的所有方法
     */
    @Pointcut("@annotation(com.liumapp.blog.aop.springboot.annotation.Login)")
    public void loginMethodPointcut(){}

    @Around("loginMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint point){
        Object result = null;
        Object[] args = point.getArgs();
        for(Object arg : args){
            if(arg instanceof HttpServletRequest){
                HttpServletRequest request = (HttpServletRequest) arg;
                if(!isLogin(request)){
                    result = new ResponseEntity("需要用户登录", Status.UNLOGIN);
                }
            }
        }
        try{
            if(result == null)
                result = point.proceed();
        }catch (Throwable e){
            result = new ResponseEntity("异常： " + e.getMessage(), Status.ERROR_EXCEPTION);
        }

        return result;
    }

    /**
     * 判断方法是否需要登录
     * @param request
     * @return
     */
    private boolean isLogin(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            String name = session.getAttribute("username").toString();
            if(name == null)
                return false;
            else
                return true;
        }catch (Exception e){
            System.out.println("aop获取session缓存中的username失败");
            return false;
        }
    }

}
