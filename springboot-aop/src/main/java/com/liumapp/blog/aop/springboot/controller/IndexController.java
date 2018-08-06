package com.liumapp.blog.aop.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.liumapp.blog.aop.springboot.annotation.CheckToken;
import com.liumapp.blog.aop.springboot.annotation.RequireAOP;
import com.liumapp.blog.aop.springboot.annotation.ReturnErrorAOP;
import com.liumapp.blog.aop.springboot.entity.HelloInfo;
import com.liumapp.blog.aop.springboot.entity.HelloTokenInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liumapp
 * @file IndexController.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/7/31
 */
@RestController
@RequestMapping("")
public class IndexController {

    @RequestMapping("/")
    public String index (@RequestParam String name, String sex) {
        return "success, get name is " + name + " and sex is : " + sex;
    }

    /**
     * no matter what values posted
     * helloinfo's value will be updated in aop
     */
    @RequireAOP
    @RequestMapping("/component")
    public String usingComponent (@RequestBody HelloInfo helloInfo) {
        return "success : " + JSON.toJSONString(helloInfo);
    }

    @ReturnErrorAOP
    @RequestMapping("/returnerror")
    public String returnError (HttpServletRequest httpServletRequest, HttpServletResponse response, @RequestBody HelloInfo helloInfo) {
        return "success" + JSON.toJSONString(helloInfo);
    }

    /**
     * check token in aop monitor
     */
    @CheckToken
    @RequestMapping("/checktoken")
    public String checkToken (@RequestBody HelloTokenInfo helloTokenInfo) {
        return "success" + JSON.toJSONString(helloTokenInfo);
    }



}
