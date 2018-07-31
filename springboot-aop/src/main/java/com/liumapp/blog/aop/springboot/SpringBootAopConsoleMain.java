package com.liumapp.blog.aop.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liumapp
 * @file SpringBootAopConsoleMain.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/7/31
 */
@SpringBootApplication
public class SpringBootAopConsoleMain {

    @Value("${custom.name:World}")
    private String name;

    public String getHelloMessage() {
        return "Hello " + this.name;
    }


}
