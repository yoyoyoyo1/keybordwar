package com.oracle.demo.controller;

import com.oracle.demo.entity.Follow;
import com.oracle.demo.entity.Share;
import com.oracle.demo.entity.User;
import com.oracle.demo.respository.UserDao;
import com.oracle.demo.service.FollowService;
import com.oracle.demo.service.ShareService;
import com.oracle.demo.service.UserService;
import com.oracle.demo.util.MD5Util;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.hibernate.validator.constraints.pl.REGON;
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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String toindex(String email,HttpSession session,Model model)
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
//    @RequestMapping("sendshare")
//    public String sendshar(@ModelAttribute Share share, HttpServletResponse response) throws IOException
//    {
//        shareService.sendShare(share,response);
//        System.out.println("用户id为："+share.getUserId()+"发布了一条动态,内容是："+share.getContent());
//        return "redirect:index";//需要跳转至动态首页控制器
//    }

    @RequestMapping("touserprofile")
    public String touserprofile(int userId,Model model,HttpSession session)
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
        Follow follow=new Follow();
        follow.setFollowering(followService.showFollowing(userId));
        follow.setFollower(followService.showFollower(userId));
        session.getAttribute("follow");
        session.setAttribute("follow",follow);
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
    //更新用户签名昵称
    @RequestMapping("updateuserinfo")
    public String updateuserinfo(@ModelAttribute User user,HttpSession session)
    {
        userService.updateInfo(user.getNickname(),user.getMotto(),user.getId());
        session.setAttribute("user",userService.findById(user.getId()));
        return "redirect:touserprofile?userId="+user.getId();
    }

    @GetMapping("/user/self")
    @ResponseBody
    public Object self(HttpSession session){
        return session.getAttribute("user");
    }
    //更新用户密码
    @RequestMapping("/userupdatepwd")
    public void userupdatepwd(@ModelAttribute User user,HttpSession session,HttpServletResponse response) throws IOException
    {
        String pass=MD5Util.encode(user.getPass());
        userService.updatePwd(pass,user.getId());
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print("<html><head><meta charset='UTF-8'></head>");
        out.print("<script>alert('修改成功，需要重新登录');window.location='/user'</script>");
        out.flush();
        out.close();
        session.getAttribute("user");
        session.invalidate();
    }
    //用户注销
    @RequestMapping("/userlogout")
    public void logout(HttpSession session,HttpServletResponse response) throws IOException
    {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print("<html><head><meta charset='UTF-8'></head>");
        out.print("<script>alert('注销成功!');window.location='/user'</script>");
        out.flush();
        out.close();
        /*User user=(User)session.getAttribute("user");
        System.out.println(user+"===========================");*/
        session.invalidate();
    }
    //用户关注他人
    @RequestMapping("/userdofollow")
    public void userpofollow(int toid,HttpSession session,HttpServletResponse response) throws IOException
    {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        User user=(User)session.getAttribute("user");
        if(followService.isFollow(user.getId(),toid)==null)
        {
            Follow follow=new Follow();
            follow.setFollower(user.getId());
            follow.setFollowering(toid);
            followService.doFollow(follow);
            out.print("<html><head><meta charset='UTF-8'></head>");
            out.print("<script>alert('关注成功!');window.location='/touserprofile?userId="+user.getId()+"'</script>");
            out.flush();
            out.close();
        }else {
            out.print("<html><head><meta charset='UTF-8'></head>");
            out.print("<script>alert('你关注过他了!');window.location='/touserprofile?userId="+user.getId()+"'</script>");
            out.flush();
            out.close();
        }
    }
    //用户取关某人
    @RequestMapping("userundofollow")
    public void userundofollow(int toid,HttpSession session,HttpServletResponse response) throws  IOException
    {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        User user=(User)session.getAttribute("user");
        followService.undoFollow(user.getId(),toid);
        out.print("<html><head><meta charset='UTF-8'></head>");
        out.print("<script>alert('你已经不再关注TA惹!');window.location='/touserprofile?userId="+user.getId()+"'</script>");
        out.flush();
        out.close();
    }
    @RequestMapping("othersprofile")
    public String othersprofile(int id,Model model,HttpSession session)
    {
        model.addAttribute("otheruser",userService.findById(id));
        User user=(User)session.getAttribute("user");
        Follow follow=new Follow();
        follow.setFollower(followService.showFollower(id));
        follow.setFollowering(followService.showFollowing(id));
        model.addAttribute("follownum",follow);
        List<Integer> followta=followService.followMeList(id);
        if(followta.size()!=0)
        {
            model.addAttribute("followta",userService.followMeList(followta));
        }else {
            model.addAttribute("followta",null);}
        List<Integer> tafollowing=followService.followingList(id);
        if(tafollowing.size()!=0)
        {
            model.addAttribute("tafollowing",userService.followMeList(tafollowing));
        }else {
            model.addAttribute("tafollowing",null);}
        model.addAttribute("tashare",shareService.findShareByIdOrderByTime(id));
        if(followService.isFollowta(user.getId(),id)==null)
        {
            model.addAttribute("isfollow",false);
        }else {
            model.addAttribute("isfollow",true);
        }
        return "user-profile";

    }
    //用户在别人主页关注他人
    @RequestMapping("/userdofollowpage")
    public void userpofollowpage(int toid,HttpSession session,HttpServletResponse response) throws IOException
    {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        User user=(User)session.getAttribute("user");
            Follow follow=new Follow();
            follow.setFollower(user.getId());
            follow.setFollowering(toid);
            followService.doFollow(follow);
            out.print("<html><head><meta charset='UTF-8'></head>");
            out.print("<script>alert('关注成功!');window.location='/othersprofile?id="+toid+"'</script>");
            out.flush();
            out.close();
    }
    //用户取关某人
    @RequestMapping("userundofollowpage")
    public void userundofollowpage(int toid,HttpSession session,HttpServletResponse response) throws  IOException
    {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        User user=(User)session.getAttribute("user");
        followService.undoFollow(user.getId(),toid);
        out.print("<html><head><meta charset='UTF-8'></head>");
        out.print("<script>alert('你已经不再关注TA惹!');window.location='/othersprofile?id="+toid+"'</script>");
        out.flush();
        out.close();
    }
    //用户点赞
    @RequestMapping("userdolike")
    @ResponseBody
    public String userdolike(int id)
    {
        Map<String,Object> map=new HashMap<String ,Object>();
        map.put("status",1);
        map.put("success",true);
       return "ok";
    }
    //用户删除动态
    @RequestMapping("userdeleteshare")
    public String deleteshare(int id,HttpSession session)
    {
        shareService.deleteshare(id);
        User user=(User)session.getAttribute("user");
        return "redirect:touserprofile?userId="+user.getId();
    }
}
