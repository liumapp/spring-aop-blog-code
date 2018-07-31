package com.liumapp.blog.aop.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file HelloWorldService.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/7/31
 */
@Component
public class HelloWorldService {

    @Value("${custom.name:World}")
    private String name;

    public String getHelloMessage() {
        System.out.println("ready to say hello to someone");
        return "Hello " + this.name;
    }

}
