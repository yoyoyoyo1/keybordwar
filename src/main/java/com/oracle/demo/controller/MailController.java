package com.oracle.demo.controller;
/*
Created by szg
*/
import com.oracle.demo.entity.User;
import com.oracle.demo.respository.UserDao;
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
    private HashMap<String,Object> recodeMap=new HashMap<>();
    @Autowired
    private UserService userService;
    @RequestMapping("user")
    public String userasd()
    {
        return "sign-in";
    }
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
    //用户注册
    @RequestMapping("userreg")
    @ResponseBody
    public String userreg(String email,String ccode,String pass,String nickname,String phone)
    {
        User user=new User();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPhone(phone);
        user.setPass(MD5Util.encode(pass));
        //默认头像
        user.setImage("default.png");
        //默认签名
        user.setMotto("编辑自己的个性签名吧");
        if(ccode.equals(codeMap.get(email)))
        {
            try {
                if(userService.findByEmail(email)==null){
                System.out.println(email +"验证码验证正确");
                userService.addOne(user);
                System.out.println(email + "注册成功");
                return "ok";} else {
                    System.out.println(email +"该邮箱已经被注册");
                    return "bad";
                }
            }catch (Exception e){
                return "error";
            }

        }
        System.out.println(email+"验证错误");
        return "";
    }
    @RequestMapping("getrecode")
    @ResponseBody
    public String getrecode(String email)
    {
        if(userService.findByEmail(email)==null)
        {
            return "bad";
        }else {
            String checkcode=String.valueOf(new Random().nextInt(899999) + 100000);
            String msg="您的找回密码验证码为 +"+checkcode;
            try {
                mailService.sendCheckCodeMail(email,"找回密码验证码",msg);
                recodeMap.put(email,checkcode);
            }catch (Exception e)
            {   //404页面
                return "404";
            }
            return "ok";
        }
    }
    @RequestMapping("userchangepwd")
    @ResponseBody
    public String userchangepwd(String email,String ccode,String pass)
    {
        if(ccode.equals(recodeMap.get(email)))
        {
            userService.changePwd(MD5Util.encode(pass),email);
            return "ok";
        }else {
            return "bad";
        }
    }
}
