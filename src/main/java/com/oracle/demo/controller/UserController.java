package com.oracle.demo.controller;

import com.oracle.demo.entity.User;
import com.oracle.demo.respository.UserDao;
import com.oracle.demo.service.UserService;
import com.oracle.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    //用户注册及密码找回在MailController(暂定)日后修改
    //用户登录
    @RequestMapping("userlogin")
    @ResponseBody
    public String userlogin(String email, String pass, HttpSession httpSession)
    {   pass=MD5Util.encode(pass);
        User user=userService.userLogin(email,pass);
        if(user==null)
        {
            return "bad";
        }
        httpSession.setAttribute("user",user);
        return "ok";
    }
    //登录后跳转主页
    @RequestMapping("toindex")
    public String toindex()
    {
        return "index";
    }
    @RequestMapping("testajax")
    @ResponseBody
    public User testajax()
    {
        User user=userService.findByEmail("shenzhigao1998@163.com");
        System.out.println(user);
        return user;
    }

}
