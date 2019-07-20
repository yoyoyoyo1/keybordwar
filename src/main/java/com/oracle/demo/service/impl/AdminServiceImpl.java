package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Admin;
import com.oracle.demo.entity.User;
import com.oracle.demo.respository.AdminDao;
import com.oracle.demo.respository.UserDao;
import com.oracle.demo.service.AdminService;
import net.sf.json.JSONArray;
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
            JSONArray json=JSONArray.fromObject(users);
            model.addAttribute("userjson",json);
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
    //批量删除用户
    public String admindelbnuser(List<Integer> id) {
        userDao.deleteUserByIdIn(id);
        if (userDao.findAllByIdIn(id).isEmpty()||userDao.findAllByIdIn(id).size()==0){
            System.out.println("批量删除成功");
            return "ok";
        }
        System.out.println("批量删除失败");
        return "bad";
    }

    @Override
    //通过修改用户id跳转
    public String toadminedituser(int id, Model model) {
        User user1=userDao.findById(id);
        model.addAttribute("adeduser",user1);
        return "admin-edituser";
    }

    @Override
    //修改用户基本信息
    public String adminedituser(User user, Model model) {
        int x=userDao.edituser(user.getEmail(),user.getPass(),user.getNickname(),user.getPhone(),user.getImage(),user.getMotto(),user.getId());
        System.out.println("修改标志:"+x);
        User user1=userDao.findById(user.getId());
        if (x!=0){
            model.addAttribute("adeduser",user1);
            model.addAttribute("msg","修改成功");
        }else{
            model.addAttribute("adeduser",user1);
            model.addAttribute("msg","修改失败");
        }
        return "admin-edituser";
    }

    @Override
    //修改用户头像为默认头像
    public String adminedituserhimg(String image, int id) {
        int x=userDao.edituserhimg(image,id);
        System.out.println("修改默认头像状态："+x);
        if (x!=0){
            return "ok";
        }else
        {
            return "bad";
        }
    }

    @Override
    //跳转到修改用户密码的页面
    public String toadminedituserpass(int id, Model model) {
        User user2=userDao.findById(id);
        model.addAttribute("adeduserpass",user2);
        return "admin-edituserpass";
    }

    @Override
    //修改用户的密码
    public String adminedituserpass(int id, String newpass, Model model) {
        int x=userDao.edituserpass(newpass,id);
        User user3=userDao.findById(id);
        if (x!=0){
            model.addAttribute("adeduserpass",user3);
            model.addAttribute("msg","修改用户密码成功");
            System.out.println("model值：修改密码成功");
        }else{
            model.addAttribute("adeduserpass",user3);
            model.addAttribute("msg","修改密码失败");
        }

        return "admin-edituserpass";
    }


}
