package com.oracle.demo.service;

import com.oracle.demo.entity.Admin;
import com.oracle.demo.entity.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AdminService {
    //管理员登录
    public String adminlogin(Admin admin,Model model,HttpSession session);
    //管理员添加用户
    public String adminadduser(User user);
    //到全部的列表
    public String toadminuserlist(Model model);
    //模糊查询昵称
    public String adminfduserbynname(String nkey,Model model);
    //模糊查询邮箱
    public String adminfduserbyemail(String ekey,Model model);
    //模糊查询手机
    public String adminfduserbyphone(String pkey,Model model);
    //批量删除用户
    public String admindelbnuser(List<Integer> id);
    //通过修改用户的id进行跳转
    public String toadminedituser(int id,Model model);
    //修改用户的信息
    public String adminedituser(User user,Model model);
    //修改用户头像为默认头像
    public String adminedituserhimg(String image,int id);
    //跳转到通过修改用户的id修改密码
    public String toadminedituserpass(int id,Model model);
    //修改用户的密码
    public String adminedituserpass(int id,String newpass,Model model);

}
