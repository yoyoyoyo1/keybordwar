package com.oracle.demo.controller;

import com.oracle.demo.entity.Admin;
import com.oracle.demo.entity.User;
import com.oracle.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ Controller
public class AdminController {
  @Autowired
    private AdminService adminService;
  //管理员登录
  @RequestMapping(value = "/adminlogin" ,method = RequestMethod.POST)
    public String adminlogin(@ModelAttribute Admin admin,Model model,HttpSession session){
    System.out.println("管理员登录："+admin.toString());
    return adminService.adminlogin(admin,model,session);
  }

  @GetMapping("gethello")
  public String xxx(){
      return "hello";
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

  //管理员退出登录，销毁全部session
  @RequestMapping("/adminlogout")
  public String logout(HttpSession session,Model model)
  {
    session.invalidate();
    model.addAttribute("msg","退出成功");
    return "admin-login";
  }
  //显示全部用户
  @RequestMapping("/toadminadduser")
  public String toadminadduser() {
    return "admin-adduser";
  }

  //添加用户
  @RequestMapping(value = "/adminadduser" ,method = RequestMethod.POST)
  public String adminadduser(@ModelAttribute User user,Model model){
    //System.out.println("添加用户的图片:"+user.getImage().equals(""));
    //System.out.println("添加用户的座右铭"+user.getMotto().equals(""));
    if (user.getImage().equals("")){
      user.setImage("default.png");
    }
    if (user.getMotto().equals(""))
    {
      user.setMotto("编辑自己的个性签名吧");
    }
    adminService.adminadduser(user);
    return "admin-index";
  }

  //查询到全部用户
  @RequestMapping("/toadminuserlist")
  public String toadminuserlist(Model model){
    String x=adminService.toadminuserlist(model);
    return x;
  }

  @RequestMapping("/tohello1")
  public String tohello(){
    return "hello1";
  }

  //昵称模糊查询
  @RequestMapping("/adminfinduserbynname")
  public String adminfduserbynname(@RequestParam(value = "nkey") String nkey,Model model){
    String key= "黑人";
    return adminService.adminfduserbynname(nkey,model);
  }

  //邮箱模糊查询
  @GetMapping("/amdinfinduserbyemail")
  public String adminfduserbyemail(Model model){
    String key="8";
    return adminService.adminfduserbyemail(key,model);
  }

  //手机模糊查询
  @GetMapping("/adminfinduserbyphone")
  public String adminfduserbyphoe(Model model){
    String key="13";
    return adminService.adminfduserbyphone(key,model);
  }

  //用户的删除
  @GetMapping("/admindeluserbyid")
  public String admindeluserbyid(Model model){
    return null;
  }

  //用户的批量删除
  @RequestMapping("/delbhuser")
  @ResponseBody
  public String del(String [] ids){
    for (String id:ids){
      System.out.println(id.toString());
    }
    List<String> idss=new ArrayList<>();
    Collections.addAll(idss,ids);
    List<String> idz=new ArrayList<>();
    List<Integer> idsss=new ArrayList<>();
    idz.add("");
    idss.removeAll(idz);
    for (String x:idss){
      System.out.println(x.toString());
    }
    System.out.println("x");
    for (String z:idss){
      int x=Integer.parseInt(z);
      idsss.add(x);
    }
    String flag=adminService.admindelbnuser(idsss);
    return flag;
  }

}
