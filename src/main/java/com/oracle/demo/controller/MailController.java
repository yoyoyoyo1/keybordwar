package com.oracle.demo.controller;
/*
Created by szg
*/
import com.oracle.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Controller
public class MailController {
    @Autowired
    private MailService mailService;
    @RequestMapping("getcheckcode")
    @ResponseBody
    public String getchechcode(String email)
    {
        String checkcode=String.valueOf(new Random().nextInt(899999) + 100000);
        String msg="您的注册验证码为 +"+checkcode;
        try {
            mailService.sendCheckCodeMail(email,"注册验证码",msg);
        }catch (Exception e)
        {   //404页面
            return "";
        }
        return checkcode;
    }
}
