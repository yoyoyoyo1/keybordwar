package com.oracle.demo.controller;
/*
Created by szg
*/
import com.oracle.demo.entity.User;
import com.oracle.demo.service.MailService;
import com.oracle.demo.service.UserService;
import com.oracle.demo.util.MD5Util;
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
    private UserService userService;
    @RequestMapping("getcheckcode")
    @ResponseBody
    public String getchechcode(String email)
    {
        System.out.println(email+"0000000000000");
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
    public String userreg(String email,String ccode,String pass,String nickname,String phone)
    {
        System.out.println(ccode);
        System.out.println(codeMap.get(email));
        User user=new User();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPhone(phone);
        user.setPass(MD5Util.encode(pass));
        if(ccode.equals(codeMap.get(email)))
        {
            System.out.println("验证正确");
            try {
                userService.addOne(user);
                System.out.println(email+"注册成功");
            }catch (Exception e)
            {
                return "404";
            }
            return "ok";

        }
        System.out.println("验证错误");
        return "";
    }

}
