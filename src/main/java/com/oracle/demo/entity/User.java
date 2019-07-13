package com.oracle.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private int id;//用户id
    private String account;//用户账号
    private String pass;//用户登录密码
    private String nickname;//用户昵称
    private String phone;//用户的手机号
    private String haddress;//用户的头像地址
    private String motto;//用户的座右铭

    public User(){}

    public User(int id, String account, String pass, String nickname, String phone, String haddress, String motto) {
        this.id = id;
        this.account = account;
        this.pass = pass;
        this.nickname = nickname;
        this.phone = phone;
        this.haddress = haddress;
        this.motto = motto;
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

    public String getHaddress() {
        return haddress;
    }

    public void setHaddress(String haddress) {
        this.haddress = haddress;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", pass='" + pass + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", haddress='" + haddress + '\'' +
                ", motto='" + motto + '\'' +
                '}';
    }
}
