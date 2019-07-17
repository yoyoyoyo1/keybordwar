package com.oracle.demo.controller;

import com.oracle.demo.entity.Follow;
import com.oracle.demo.entity.Share;
import com.oracle.demo.entity.User;
import com.oracle.demo.respository.UserDao;
import com.oracle.demo.service.FollowService;
import com.oracle.demo.service.ShareService;
import com.oracle.demo.service.UserService;
import com.oracle.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    //用户注册及密码找回在MailController(暂定)日后修改
    //用户登录
    @Autowired
    private FollowService followService;
    @Autowired
    private ShareService shareService;
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
    public String toindex(String email,HttpSession session)
    {
        Follow follow=new Follow();
        int id=userService.findIdByEmail(email);
        follow.setFollowering(followService.showFollowing(id));
        follow.setFollower(followService.showFollower(id));
        session.setAttribute("follow",follow);
        return "redirect:index";//需要跳转至动态主页的控制器
    }
    /*@RequestMapping("testajax")
    @ResponseBody
    //ajax的测试
    public User testajax()
    {
        User user=userService.findByEmail("shenzhigao1998@163.com");
        System.out.println(user);
        return user;
    }*/
    @RequestMapping("sendshare")
    public String sendshar(@ModelAttribute Share share, HttpServletResponse response) throws IOException
    {
        shareService.sendShare(share,response);
        System.out.println("用户id为："+share.getUserId()+"发布了一条动态,内容是："+share.getContent());
        return "redirect:index";//需要跳转至动态首页控制器
    }
    @RequestMapping("touserprofile")
    public String touserprofile(int userId,Model model)
    {   model.addAttribute("share",shareService.findShareByIdOrderByTime(userId));
        List<Integer> followme=followService.followMeList(userId);
        if(followme.size()!=0)
        {
            model.addAttribute("followme",userService.followMeList(followme));
        }else {
        model.addAttribute("followme",null);}
        List<Integer> following=followService.followingList(userId);
        if(following.size()!=0)
        {
            model.addAttribute("following",userService.followMeList(following));
        }else {
        model.addAttribute("following",null);}
        return "my-profile-feed";
    }
    @RequestMapping("userupdateimg")
    public String userupdateimg(@RequestParam("userimage") MultipartFile file,@ModelAttribute User user,HttpSession session)
    {   System.out.println("-------------------------"+user.getId());
        if(file.isEmpty())
        {
            return "redirect:touserprofile?userId="+user.getId();
        }
        String fileName=MD5Util.encode(user.getId()+"")+file.getOriginalFilename();
        //相对地址
        String filePath= ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/userimage/";
        File dest=new File(filePath + fileName);
        try {
            file.transferTo(dest);
            userService.changeImg(fileName,user.getId());
            session.setAttribute("user",userService.findById(user.getId()));
            System.out.println("此用户"+user.getId()+"更新了自己的头像 。");
            return "redirect:touserprofile?userId="+user.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:touserprofile?userId="+user.getId();
    }
    //跳转到修改个人信息页面 发送model
    @RequestMapping("tousersetting")
    public String tousersetting(int id,Model model)
    {
        model.addAttribute("userinfo",userService.findById(id));
        return "profile-account-setting";
    }
    @RequestMapping("updateuserinfo")
    public String updateuserinfo(@ModelAttribute User user,HttpSession session)
    {
        userService.updateInfo(user.getNickname(),user.getMotto(),user.getId());
        session.setAttribute("user",userService.findById(user.getId()));
        return "redirect:touserprofile?userId="+user.getId();
    }

}
