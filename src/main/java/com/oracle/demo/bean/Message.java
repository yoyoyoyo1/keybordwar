package com.oracle.demo.bean;
import com.alibaba.fastjson.JSON;

public class Message {
    private String name;
    private String message;
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}