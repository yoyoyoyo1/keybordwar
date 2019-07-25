package com.oracle.demo.controller;

import com.oracle.demo.entity.Admin;
import com.oracle.demo.entity.User;
import com.oracle.demo.respository.AdminDao;
import com.oracle.demo.respository.UserDao;
import com.oracle.demo.service.AdminService;
import com.oracle.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {
  @Autowired
    private AdminService adminService;
  @Autowired
  private UserDao userDao;
  @Autowired
  private AdminDao adminDao;
  //管理员登录
  @RequestMapping(value = "/adminlogin" ,method = RequestMethod.POST)
    public String adminlogin(@ModelAttribute Admin admin,Model model,HttpSession session){
    System.out.println("管理员登录："+admin.toString());
    return adminService.adminlogin(admin,model,session);
  }

  @RequestMapping("/gethello")
  public String xxx(@RequestParam(value = "xx") String xx){
    System.out.println(xx);
      return "admin-welcome";
  }
  @GetMapping("/gethello1")
  public String zz(@RequestParam(value = "start") String start,@RequestParam(value = "end") String end){
    System.out.println(start+","+end);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String start1=start+" 00:00:00";
    String end1=end+" 00:00:00";
    System.out.println(start1);
    return "hello";

  }

  @RequestMapping("/toadminlogin")
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
    String passmd5= MD5Util.encode(user.getPass());
    user.setPass(passmd5);
    System.out.println("添加用户的密码通过md5加密为："+passmd5);
    adminService.adminadduser(user);
    model.addAttribute("msg","添加用户成功");
    return "admin-adduser";
  }

  /*
  //查询到全部用户
  @RequestMapping("/toadminuserlist")
  public String toadminuserlist(Model model){
    String x=adminService.toadminuserlist(model);
    return x;
  }
  */

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

  //返回用户的昵称
  @RequestMapping("/getnkname")
  @ResponseBody
  public List<String> getnkname(String nkey){
     List<User> users=userDao.findAllByNicknameLike(nkey+"%");
     List<String> nk=new ArrayList<>();
     for (User user:users){
       nk.add(user.getNickname());
     }
    System.out.println("补全用户昵称的查询:"+nk.toString());
    return nk;
  }

  //通过修改修改用户id的跳转
  @RequestMapping("/toadminedituser")
  public String toadminedituser(int id,Model model){
    System.out.println("编辑用户的id"+id);
    return adminService.toadminedituser(id,model);
  }

  //修改用户基本
  @RequestMapping("/adminedituser")
  public String adminedituser(@ModelAttribute User user,Model model){
    return adminService.adminedituser(user,model);
  }

  //修改用户头像为默认头像
  @RequestMapping("/adminedithimage")
  @ResponseBody
  public String adminedithimage(int id){
    String mimage="default.png";
    System.out.println("修改默认头像id："+id);
    return adminService.adminedituserhimg(mimage,id);
  }

  //跳转到修改用户密码
  @RequestMapping("/toadmineditpass")
  public String toadmineditpass(int id,Model model){
    System.out.println("修改用户的id:"+id);
     return adminService.toadminedituserpass(id,model);
  }

  //修改用户密码
  @RequestMapping("/admineditpass")
  public String admineditpass(@RequestParam(value = "adminid") int adminid,@RequestParam(value = "adminpass") String  adminpass,@RequestParam(value = "id") int id,@RequestParam(value = "newpass") String newpass,Model model)
  {
    String newpassMD5=MD5Util.encode(newpass);
    System.out.println("修改用户的新密码:"+newpassMD5);
    return adminService.adminedituserpass(adminid,adminpass,id,newpassMD5,model);
  }

  //全部用户的分页显示
  /*
  @RequestMapping("/toadminuserlistbypage")
  public String  toadminuserlistbtpage(@RequestParam(value = "pagenum",required=false) String pagenum,Model model){
    if (pagenum==null||"".equals(pagenum)){
      pagenum="0";
    }
    int pagenumn=Integer.parseInt(pagenum);
    int pagesize=5;
    ModelAndView modelAndView=new ModelAndView();
    Pageable pageable=PageRequest.of(pagenumn,pagesize);
    System.out.println("这里这里");
    Page<User> userPage=userDao.findAll(pageable);
    List<User> usersPagelist=userPage.getContent();
    model.addAttribute("Userlist",usersPagelist);
    //总记录数
    model.addAttribute("totalpagenum",userPage.getTotalElements());
    //当前页码
    model.addAttribute("pagenum",pagenumn);
    System.out.println("当前页"+pagenumn);
    //每页多少数量
    model.addAttribute("pagesize",pagesize);
    //总页数
    model.addAttribute("totalpages",userPage.getTotalPages()-1);
    return "admin-userlist";
  }
*/
  //模糊查询昵称的分页显示
  @RequestMapping("/toadminuserlistbypage")
  public String adminfinduserbynnamepage(@RequestParam(value = "pagenum",required=false) String pagenum,@RequestParam(value = "nkey",required = false) String nkey,Model model){
    if (pagenum==null||"".equals(pagenum)){
      pagenum="0";
    }
    if (nkey==null||"".equals(nkey)){
      nkey="";
    }
    System.out.println("查询关键字"+nkey);
    model.addAttribute("nkey",nkey);
    int pagenumn=Integer.parseInt(pagenum);
    int pagesize=5;
    return adminService.adminfduserbynamepage(nkey,pagenumn,pagesize,model);
  }

  //删除用户
  @RequestMapping("/admindeluser")
  @ResponseBody
  public String admindeluser( int id){
    System.out.println("要删除用户的id:"+id);
    return adminService.admindeluser(id);
  }
  //查看用户动态
  @RequestMapping("/admintouserdt")
  public String admintouserdt(@RequestParam(value = "start",required = false) String start,@RequestParam(value = "end",required = false) String end,@RequestParam(value = "id") int id,@RequestParam(value = "pagenum",required=false) String pagenum,Model model){
    System.out.println("查看用户动态的id："+id);
    if (pagenum==null||"".equals(pagenum)){
      pagenum="0";
    }
    int pagenumn=Integer.parseInt(pagenum);
    int pagesize=10;
    if ( start == null){
      start="2000-1-1";
    }
    if ( end == null){
      end="2999-1-1";
    }
    model.addAttribute("id",id);
    System.out.println(start);
    System.out.println(end);
    model.addAttribute("start",start);
    model.addAttribute("end",end);
    return adminService.admintouserdt(start,end,id,pagenumn,pagesize,model);
  }

  //批量删除某一用户的动态
  @RequestMapping("/delbhshare")
  @ResponseBody
  public String delbhshare(String [] ids){
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
    String flag=adminService.admindelbhshare(idsss);
    return flag;
  }

  @RequestMapping("/adminshowuserdt")
  public String adminshowuserdt(int id,Model model){
    return adminService.amdinshowuserdt(id,model);
  }

  //查看全部动态
  @RequestMapping("/toallshare")
  public String toallshare(@RequestParam(value = "pagenum",required=false) String pagenum,@RequestParam(value = "nkey",required = false) String nkey,Model model){
    if (pagenum==null||"".equals(pagenum)){
      pagenum="0";
    }
    if (nkey==null||"".equals(nkey)){
      nkey="";
    }
    System.out.println("查询关键字"+nkey);
    model.addAttribute("nkey",nkey);
    int pagenumn=Integer.parseInt(pagenum);
    int pagesize=10;
    return adminService.toallshare(nkey,pagenumn,pagesize,model);
  }
}
