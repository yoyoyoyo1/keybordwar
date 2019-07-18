package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Admin;
import com.oracle.demo.entity.User;
import com.oracle.demo.respository.AdminDao;
import com.oracle.demo.respository.UserDao;
import com.oracle.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminDao adminDao;
    @Autowired
    UserDao userDao;

    @Override
    //管理员登录
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
    //添加用户
    public String adminadduser(User user) {
        User flag=adminDao.save(user);
        System.out.println("添加用户的信息："+flag);
        return "flag";
    }

    @Override
    //查询全部用户
    public String toadminuserlist(Model model) {
        List<User> users=userDao.findAllBy();
            System.out.println("查询用户全部列表："+users.toString());
            model.addAttribute("Userlist",users);
        return "admin-userlist";
    }

    @Override
    //昵称模糊查询用户
    public String adminfduserbynname(String nkey, Model model) {
        List<User> usersbylikenname=userDao.findAllByNicknameLike("%"+nkey+"%");
        System.out.println("昵称模糊查询用户列表："+usersbylikenname.toString());
        model.addAttribute("Userlist",usersbylikenname);
        return "admin-userlist";
    }

    @Override
    //邮箱昵称模糊查询用户
    public String adminfduserbyemail(String ekey, Model model) {
        List<User> usersbylikeemail=userDao.findAllByEmailLike("%"+ekey+"%");
        System.out.println("邮箱模糊查询用户列表"+usersbylikeemail.toString());
        model.addAttribute("Userlist",usersbylikeemail);
        return "admin-userlist";
    }

    @Override
    //手机模糊查询用户
    public String adminfduserbyphone(String pkey, Model model) {
        List<User> usersbylikephone=userDao.findAllByPhoneLike("%"+pkey+"%");
        System.out.println("手机模糊查询用户列表"+usersbylikephone.toString());
        model.addAttribute("Userlist",usersbylikephone);
        return "admin-userlist";
    }

    @Override
    public String admindelbnuser(List<Integer> id) {
        userDao.deleteUserByIdIn(id);
        if (userDao.findAllByIdIn(id).isEmpty()||userDao.findAllByIdIn(id).size()==0){
            System.out.println("批量删除成功");
            return "ok";
        }
        System.out.println("批量删除失败");
        return "bad";
    }

}
