package com.liumapp.blog.aop.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
