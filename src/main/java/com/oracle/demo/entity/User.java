package com.oracle.demo.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private int id;//用户id
    private String pass;//用户登录密码
    private String email;
    private String nickname;//用户昵称
    private String phone;//用户的手机号
    private String image;//用户的头像地址
    private String motto;//用户的座右铭

    public User(){}

    public User(int id, String email,String pass, String nickname, String phone, String image, String motto) {
        this.id = id;
        this.pass = pass;
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.image = image;
        this.motto = motto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
