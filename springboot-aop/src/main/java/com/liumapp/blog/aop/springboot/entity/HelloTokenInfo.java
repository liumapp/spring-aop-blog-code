package com.liumapp.blog.aop.springboot.entity;

import org.springframework.stereotype.Component;

/**
 * @author liumapp
 * @file HelloTokenInfo.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/8/6
 */
@Component
public class HelloTokenInfo {

    private String token;

    private String name;

    private String sex;

    public HelloTokenInfo() {
    }

    public HelloTokenInfo(String token, String name, String sex) {
        this.token = token;
        this.name = name;
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public HelloTokenInfo setToken(String token) {
        this.token = token;
        return this;
    }

    public String getName() {
        return name;
    }

    public HelloTokenInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public HelloTokenInfo setSex(String sex) {
        this.sex = sex;
        return this;
    }
}
