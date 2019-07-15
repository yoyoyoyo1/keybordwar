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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class MailController {
    @Autowired
    private MailService mailService;
    private HashMap<String,Object> codeMap = new HashMap<>();
    @RequestMapping("getcheckcode")
    @ResponseBody
    public String getchechcode(String email)
    {
        String checkcode=String.valueOf(new Random().nextInt(899999) + 100000);
        String msg="您的注册验证码为 +"+checkcode;
        try {
            mailService.sendCheckCodeMail(email,"注册验证码",msg);
            codeMap.put(email,checkcode);
        }catch (Exception e)
        {   //404页面
            return "404";
        }
        return checkcode;
    }
    @RequestMapping("userreg")
    @ResponseBody
    public String userreg(String email,String ccode)
    {
        System.out.println(ccode);
        System.out.println(codeMap.get(email));
        if(ccode.equals(codeMap.get(email)))
        {
            System.out.println("验证正确");
            return "ok";
        }
        System.out.println("验证错误");
        return "";
    }
}
