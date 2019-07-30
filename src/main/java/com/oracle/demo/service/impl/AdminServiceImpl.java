package com.oracle.demo.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.oracle.demo.entity.*;
import com.oracle.demo.respository.*;
import com.oracle.demo.service.AdminService;
import com.oracle.demo.util.StringUtil;
import com.oracle.demo.websocket.dialogWebSocket;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminDao adminDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ShareDao shareDao;
    @Autowired
    SharePictureDao sharePictureDao;
    @Autowired
    DialogDao dialogDao;
    @Autowired
    ShareInfoDao shareInfoDao;
    @Override
    //管理员登录
    public String adminlogin(Admin admin,Model model,HttpSession session) {
        if (adminDao.findAdminByAccountAndPassword(admin.getAccount(),admin.getPassword()) != null){

            model.addAttribute("msg","登录成功");
             Admin admin1=adminDao.findAdminByAccountAndPassword(admin.getAccount(),admin.getPassword());
             session.setAttribute("admining",admin1);
            int sharenum=shareInfoDao.countShareNum();
            int usernum=adminDao.usernum();
            int dialognum=adminDao.dialognum();
            session.setAttribute("sharenum",sharenum);
            session.setAttribute("usernum",usernum);
            session.setAttribute("dialognum",dialognum);
            System.out.println(usernum+"lll"+sharenum+"hhh"+dialognum);
             return "admin/admin-index";


        }
        model.addAttribute("msg","登录失败，账号或密码错误");
        return "admin/admin-login";
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
        return "admin/admin-userlist";
    }

    @Override
    //昵称模糊查询用户
    public String adminfduserbynname(String nkey, Model model) {
        List<User> usersbylikenname=userDao.findAllByNicknameLike("%"+nkey+"%");
        System.out.println("昵称模糊查询用户列表："+usersbylikenname.toString());
        model.addAttribute("Userlist",usersbylikenname);
        return "admin/admin-userlist";
    }

    @Override
    //邮箱昵称模糊查询用户
    public String adminfduserbyemail(String ekey, Model model) {
        List<User> usersbylikeemail=userDao.findAllByEmailLike("%"+ekey+"%");
        System.out.println("邮箱模糊查询用户列表"+usersbylikeemail.toString());
        model.addAttribute("Userlist",usersbylikeemail);
        return "admin/admin-userlist";
    }

    @Override
    //手机模糊查询用户
    public String adminfduserbyphone(String pkey, Model model) {
        List<User> usersbylikephone=userDao.findAllByPhoneLike("%"+pkey+"%");
        System.out.println("手机模糊查询用户列表"+usersbylikephone.toString());
        model.addAttribute("Userlist",usersbylikephone);
        return "admin/admin-userlist";
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
        return "admin/admin-edituser";
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
        return "admin/admin-edituser";
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
        return "admin/admin-edituserpass";
    }

    @Override
    //修改用户的密码
    public String adminedituserpass(int adminid,String adminpass,int id, String newpass, Model model) {
        int x=userDao.edituserpass(newpass,id);
        Admin admin1=adminDao.findAdminByIdAndPassword(adminid,adminpass);
        User user3=userDao.findById(id);
        if (admin1 != null){
        if (x!=0){
            model.addAttribute("msg","修改用户密码成功");
            System.out.println("model值：修改密码成功");
        }else{
            model.addAttribute("msg","修改密码失败");
        }
        }else {
            model.addAttribute("msg", "输入的管理员密码错误");
        }
        model.addAttribute("adeduserpass",user3);
        return "admin/admin-edituserpass";
    }

    @Override
    //模糊查询昵称进行分页
    public String adminfduserbynamepage(String name, int pagenum, int pagesize,Model model) {
        String nkey="%"+name+"%";
        Pageable pageable= PageRequest.of(pagenum,pagesize);
        Specification<User> spec=new Specification<User>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p1=criteriaBuilder.like(root.get("nickname").as(String.class),nkey);
                return p1;

            }
        };
        Page<User> userPage=userDao.findAll(spec,pageable);
        List<User> usersPagelist=userPage.getContent();
        model.addAttribute("Userlist",usersPagelist);
        //总记录数
        model.addAttribute("totalpagenum",userPage.getTotalElements());
        //当前页码
        model.addAttribute("pagenum",pagenum);
        System.out.println("当前页"+pagenum);
        //每页多少数量
        model.addAttribute("pagesize",pagesize);
        //总页数
        model.addAttribute("totalpages",userPage.getTotalPages()-1);

        System.out.println("按已查询的nkey"+name);
        return "admin/admin-userlist";
    }

    @Override
    //管理员删除用户
    public String admindeluser(int id) {
        int x=userDao.deleteUserById(id);
        if (x!=0){
            return "ok";
        }else{
            return "bad";
        }
    }

    @Override
    //查看用户的个人动态
    public String admintouserdt(String start,String end,int id, int pagenum, int pagesize,Model model) {
        List<Share> shares=shareDao.findAllByUserId(id);
        System.out.println(shares.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Pageable pageable= PageRequest.of(pagenum,pagesize);
        Specification<Share> spec=new Specification<Share>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Share> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> p=new ArrayList<>();

                try {
                    Predicate p1=criteriaBuilder.equal(root.get("userId"),id);
                    System.out.println("hhh"+id);
                    p.add(p1);
                    if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end)) {
                        String start1=start+" 00:00:00";
                        String end1=end+" 00:00:00";
                        Date startt = sdf.parse(start1);
                        Date endt = sdf.parse(end1);
                        Predicate p2 = criteriaBuilder.between(root.get("createdAt"), startt, endt);
                        p.add(p2);
                    }
                     return criteriaBuilder.and(p.toArray(new Predicate[p.size()]));
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("sadf"+p.toString());
                return null;
            }
        };
        Page<Share> shares1=shareDao.findAll(spec,pageable);
        List<Share> sharepage=shares1.getContent();
        model.addAttribute("sharepage",sharepage);
        //总记录数
        model.addAttribute("totalpagenum",shares1.getTotalElements());
        //当前页码
        model.addAttribute("pagenum",pagenum);
        System.out.println("当前页"+pagenum);
        //每页多少数量
        model.addAttribute("pagesize",pagesize);
        //总页数
        model.addAttribute("totalpages",shares1.getTotalPages()-1);
        model.addAttribute("usershare",shares);
        return "admin/admin-usershare";

    }

    @Override
    //批量删除某一用户的动态
    public String admindelbhshare(List<Integer> id) {
        shareDao.deleteShareByIdIn(id);
        if (shareDao.findAllByIdIn(id).isEmpty()||shareDao.findAllByIdIn(id).size()==0){
            System.out.println("批量删除成功");
            return "ok";
        }
        System.out.println("批量删除失败");
        return "bad";
    }

    @Override
    public String amdinshowuserdt(int id, Model model) {
        List<SharePicture> sharePictures=sharePictureDao.findSharePictureByshareId(id);
        String cont=shareDao.findcontent(id);
        model.addAttribute("sharep",sharePictures);
        model.addAttribute("cont",cont);
        System.out.println(sharePictures.toString());
        System.out.println("内容"+cont);
        return "hello";
    }


    @Override
    //查看全部动态
    public String toallshare(String start,String end,String nkey,int pagenum,int pagesize,Model model) {
        Pageable pageable= PageRequest.of(pagenum,pagesize);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Specification<Share> spec=new Specification<Share>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Share> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> p=new ArrayList<>();

                try {
                    if (StringUtils.isNotEmpty(nkey) && nkey!=null){
                        Join<Share,User> userJoin=root.join("user",JoinType.LEFT);
                        Predicate p1=criteriaBuilder.like(userJoin.get("nickname"),"%"+nkey+"%");
                        p.add(p1);
                    }
                    if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end)) {
                        String start1=start+" 00:00:00";
                        String end1=end+" 00:00:00";
                        Date startt = sdf.parse(start1);
                        Date endt = sdf.parse(end1);
                        Predicate p2 = criteriaBuilder.between(root.get("createdAt"), startt, endt);
                        p.add(p2);
                    }
                    return criteriaBuilder.and(p.toArray(new Predicate[p.size()]));
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("sadf"+p.toString());
                return null;
                //Join<Share,User> userJoin=root.join("user",JoinType.LEFT);
                //Predicate p=criteriaBuilder.like(userJoin.get("nickname"),"%"+nkey+"%");
            }
        };
        Page<Share> shares1=shareDao.findAll(spec,pageable);
        List<Share> shares=shares1.getContent();
        System.out.println("zzz"+shares.toString());
        //总记录数
        model.addAttribute("totalpagenum",shares1.getTotalElements());
        //当前页码
        model.addAttribute("pagenum",pagenum);
        System.out.println("当前页"+pagenum);
        //每页多少数量
        model.addAttribute("pagesize",pagesize);
        //总页数
        model.addAttribute("totalpages",shares1.getTotalPages()-1);
        model.addAttribute("usershare",shares);

        return "admin/admin-allshare";
    }

    @Override
    // 添加圆桌
    public String adminadddialog(Dialog dialog, Model model) {
        Dialog dialog1=adminDao.save(dialog);
        dialogWebSocket.Dialog.put(dialog1.getId(),new HashMap<>());
        dialogWebSocket.dialogs.put(dialog1.getId(),dialog1);
        model.addAttribute("msg","添加圆桌成功");
        return "admin/admin-adddialog";
    }

    @Override
    //查看全部的圆桌
    public String admindialoglist(String start,String end,String nkey,int pagenum,int pagesize,Model model) {
       Pageable pageable=PageRequest.of(pagenum,pagesize);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Specification<Dialog> spec=new Specification<Dialog>() {
           @Nullable
           @Override
           public Predicate toPredicate(Root<Dialog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
               List<Predicate> p=new ArrayList<>();

               try {
                   if (StringUtils.isNotEmpty(nkey) && nkey!=null){
                       Predicate p1=criteriaBuilder.like(root.get("title"),"%"+nkey+"%");
                       p.add(p1);
                   }
                   if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end)) {
                       String start1=start+" 00:00:00";
                       String end1=end+" 00:00:00";
                       Date startt = sdf.parse(start1);
                       Date endt = sdf.parse(end1);
                       Predicate p2 = criteriaBuilder.between(root.get("createdAt"), startt, endt);
                       p.add(p2);
                   }
                   return criteriaBuilder.and(p.toArray(new Predicate[p.size()]));
               }catch (Exception e){
                   e.printStackTrace();
               }
               System.out.println("sadf"+p.toString());
               return null;
           }
           };
        Page<Dialog> dialogs =dialogDao.findAll(spec,pageable);
        List<Dialog> dialogpage=dialogs.getContent();
        model.addAttribute("dialogpage",dialogpage);
        //总记录数
        model.addAttribute("totalpagenum",dialogs.getTotalElements());
        //当前页码
        model.addAttribute("pagenum",pagenum);
        System.out.println("当前页"+pagenum);
        //每页多少数量
        model.addAttribute("pagesize",pagesize);
        //总页数
        model.addAttribute("totalpages",dialogs.getTotalPages()-1);

       return "admin/admin-dialoglist";


    }

    @Override
    //批量删除圆桌
    public String admindelbhdialog(List<Integer> id) {
        dialogDao.deleteDialogByIdIn(id);
        if (dialogDao.findAllByIdIn(id).isEmpty()||dialogDao.findAllByIdIn(id).size()==0){
            System.out.println("批量删除成功");
            return "ok";
        }
        System.out.println("批量删除失败");
        return "bad";
    }

    @Override
    //编辑圆桌
    public String admineditdia(int id,String title,String content,String image, Model model) {
        int x=dialogDao.updateDialog(title,content,image,id);
        Dialog dialog=new Dialog();
        if (x!=0){
            model.addAttribute("msg","修改成功");
            dialog=dialogDao.findById(id);
        }else {
            model.addAttribute("msg","修改失败");
            dialog=dialogDao.findById(id);
        }
        System.out.println("编辑后"+dialog.toString());
        model.addAttribute("dialogedit",dialog);
        return "admin/admin-editdialog";
    }

    @Override
    public String toshowshare(int id, Model model) {
        Share share=shareDao.findById(id);
        int  userid=share.getUserId();
        User user=userDao.findById(userid);
        List<SharePicture> sharep=sharePictureDao.findSharePictureByshareId(id);
        model.addAttribute("suser",user);
        model.addAttribute("sshare",share);
        model.addAttribute("ssharep",sharep);
        return "admin/admin-showsharect";
    }
}
