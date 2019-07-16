package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Admin;
import com.oracle.demo.entity.User;
import com.oracle.demo.respository.AdminDao;
import com.oracle.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminDao adminDao;
    @Autowired
    UserDao userDao;

    @Override
    public String adminlogin(Admin admin,Model model,HttpSession session) {
        if (adminDao.findAdminByAccountAndPassword(admin.getAccount(),admin.getPassword()) != null){
             model.addAttribute("msg","登录成功");
             Admin admin1=adminDao.findAdminByAccountAndPassword(admin.getAccount(),admin.getPassword());
             session.setAttribute("admining",admin1);
             return "admin-index";

        }
        model.addAttribute("msg","登录失败，账号或密码错误");
        return "admin-login";
    }

    @Override
    public String adminadduser(User user) {
        User flag=adminDao.save(user);
        System.out.println(flag);
        return "flag";
    }

    @Override
    public String toadminuserlist(Model model, HttpSession session) {
        List<User> users=userDao.findAllBy();
            System.out.println(users.toString());
            session.setAttribute("Userslist",users);
        return "userlist";
    }
}
