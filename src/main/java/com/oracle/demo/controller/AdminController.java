package com.oracle.demo.controller;

import com.oracle.demo.entity.Admin;
import com.oracle.demo.entity.User;
import com.oracle.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@ Controller
public class AdminController {
  @Autowired
    private AdminService adminService;

  @RequestMapping(value = "/adminlogin" ,method = RequestMethod.POST)
    public String adminlogin(@ModelAttribute Admin admin,Model model,HttpSession session){
    System.out.println(admin.toString());
    return adminService.adminlogin(admin,model,session);
  }
  @GetMapping("/toadminlogin")
  public String login(){
      return "admin-login";
  }
  @GetMapping("/getwelcome")
  public String welcome()
  {
    return "admin-welcome";
  }
  @RequestMapping("/adminlogout")
  public String logout(HttpSession session,Model model)
  {
    session.invalidate();
    model.addAttribute("msg","退出成功");
    return "admin-login";
  }

  @RequestMapping("/toadminadduser")
  public String toadminadduser() {
    return "admin-adduser";
  }

  @RequestMapping(value = "/adminadduser" ,method = RequestMethod.POST)
  public String adminadduser(@ModelAttribute User user,Model model){
    adminService.adminadduser(user);
    return "admin-index";
  }

  @RequestMapping("toadminuserlist")
  public String toadminuserlist(Model model,HttpSession session){
    String x=adminService.toadminuserlist(model,session);
    return x;
  }


}
