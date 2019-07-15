package com.oracle.demo.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;

@Entity
public class Admin {
     @GeneratedValue(strategy= GenerationType.IDENTITY)
     @javax.persistence.Id
     private int id;//管理员id
     @Column(length=20)
     private String account;//管理员账号
     @Column(length=64)
     private String password;//管理员密码
     @Column(length=20)
     private String name;//管理员姓名

    public Admin(){}

    public Admin(int id, String account, String password, String name) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
