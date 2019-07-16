package com.oracle.demo.service;

import com.oracle.demo.entity.Admin;
import com.oracle.demo.entity.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface AdminService {
    public String adminlogin(Admin admin,Model model,HttpSession session);
    public String adminadduser(User user);
    public String toadminuserlist(Model model,HttpSession session);
}
