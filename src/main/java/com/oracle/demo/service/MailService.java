package com.oracle.demo.service;
/*
      Created By szg
*/
public interface MailService {
    //发送注册验证码
    public void sendCheckCodeMail(String to, String title, String content);
    //发送重置密码验证码
}
