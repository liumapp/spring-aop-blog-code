package com.liumapp.blog.aop.springboot.response;

import com.liumapp.blog.aop.springboot.status.Status;

/**
 * @author liumapp
 * @file ResponseEntity.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/8/3
 */
public class ResponseEntity {

    private String msg;

    private Status status;

    public ResponseEntity() {
    }

    public ResponseEntity(String msg, Status status) {
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseEntity setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public ResponseEntity setStatus(Status status) {
        this.status = status;
        return this;
    }
}
