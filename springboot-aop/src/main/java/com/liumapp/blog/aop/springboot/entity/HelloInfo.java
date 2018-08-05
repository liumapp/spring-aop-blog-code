package com.liumapp.blog.aop.springboot.entity;

import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file HelloInfo.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/8/5
 */
@Component
public class HelloInfo {

    private String name;

    private String sex;

    public HelloInfo() {
    }

    public HelloInfo(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public HelloInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public HelloInfo setSex(String sex) {
        this.sex = sex;
        return this;
    }
}
