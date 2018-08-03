package com.liumapp.blog.aop.springboot.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

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

    /**
     * 拦截器具体实现
     * @param point
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("loginMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint point){

        //正在被通知的方法相关信息
        MethodSignature signature = (MethodSignature) point.getSignature();
        //获取被拦截的方法
        Method method = signature.getMethod();
        //获取被拦截的方法名
        String methodName = method.getName();
        //返回的结果
        Object result = null;
        //返回方法参数
        Object[] args = point.getArgs();
        for(Object arg : args){
            //获取request请求
            if(arg instanceof HttpServletRequest){
                HttpServletRequest request = (HttpServletRequest) arg;
                //System.out.println(isLogin(request));
                //判断用户是否登录
                if(!isLogin(request)){
                    result = 
                    //JsonResult与ResultCode是我自己封装的返回类及enum类
//                    result =
//                    result = new JsonResult(ResultCode.NOT_LOGIN, "没有登录", null);
                }
            }
        }
        try{
            if(result == null)
                // 一切正常的情况下，继续执行被拦截的方法
                result = point.proceed();
        }catch (Throwable e){
            result = new JsonResult(ResultCode.EXCEPTION, "发生异常："+e.getMessage());
        }

        return result;
    }

    /**
     * 判断方法是否需要登录
     * @param request
     * @return
     */
    private boolean isLogin(HttpServletRequest request){
        try {
            //将cookie中的用户信息取出
            //CooikesHandle类是我自己封装的一个处理Cookie的类
            CookiesHandle cookiesHandle = new CookiesHandle(request, null);
            String uuid = cookiesHandle.getCookieByName("uuid").getValue();
            String name = cookiesHandle.getCookieByName("last_login_username").getValue();
            //将redis缓存中的用户信息取出
            String token = null;
            //System.out.println(name);
            token  = stringRedisTemplate.opsForValue().get("token:" + name);
            //System.out.println(token);
            if(token == null)
                return false;
            else if(token.equals(uuid))
                return true;
        }catch (Exception e){
            System.out.println("aop获取redis缓存中的token失败");
            return false;
        }
        return false;
    }

}
