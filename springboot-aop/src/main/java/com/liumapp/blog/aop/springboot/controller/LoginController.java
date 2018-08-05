package com.liumapp.blog.aop.springboot.controller;

import com.liumapp.blog.aop.springboot.annotation.Login;
import com.liumapp.blog.aop.springboot.response.ResponseEntity;
import com.liumapp.blog.aop.springboot.status.Status;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author liumapp
 * @file LoginController.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/8/3
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @RequestMapping(value = "/login")
    public ResponseEntity login(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam String name, @RequestParam String pass){
        try{
            if(name != null && pass != null) {
                HttpSession session = request.getSession();
                session.setAttribute("username", name);
                return new ResponseEntity("登陆成功", Status.LOGIN_SUCCESS);
            } else
                return new ResponseEntity("登陆失败", Status.LOGIN_FAILED);
        }catch (Exception e){
            return new ResponseEntity("登陆失败", Status.ERROR_EXCEPTION);
        }
    }

    @PostMapping(value = "/isLogin")
    @Login()
    public ResponseEntity isLogin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession();
        String name = httpSession.getAttribute("username").toString();
        try {
            if (name == null)
                return new ResponseEntity("用户未登录", Status.UNLOGIN);
            else
                return new ResponseEntity("用户已登录", Status.LOGIN_SUCCESS);
        }catch (Exception e){
            return new ResponseEntity("用户不存在", Status.ERROR_EXCEPTION);
        }
    }

}
