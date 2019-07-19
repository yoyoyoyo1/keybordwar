package com.oracle.demo.controller;


import com.oracle.demo.entity.Share;
import com.oracle.demo.entity.ShareInfo;
import com.oracle.demo.entity.User;
import com.oracle.demo.respository.LikeDao;
import com.oracle.demo.service.impl.ShareServiceImpl;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ShareController {
    @Autowired
    private ShareServiceImpl shareService;
    @Autowired
    LikeDao likeDao;


    @RequestMapping("/index")
    public String toIndex(Model model, HttpSession session){
        User user=(User)session.getAttribute("user");
        int userid=user.getId();
        List<ShareInfo> shareList=shareService.getAll();
        System.out.println("查找成功");
        for (int i=0;i<shareList.size();i++){
            if(likeDao.findLike(shareList.get(i).getId(),userid)==null)
            {
                shareList.get(i).setLikeInfo(0);
                System.out.println(shareList.get(i).getLikeInfo()+"pjj没jj测试成功");
            }else {
                shareList.get(i).setLikeInfo(1);
            }

        }

        model.addAttribute("shareList",shareList);
        return "index";
    }

    @RequestMapping("sendshare")
    public String sendshare(@ModelAttribute Share share, HttpServletResponse response) throws IOException
    {
        shareService.sendShare(share,response);
        System.out.println("用户id为："+share.getUserId()+"发布了一条动态,内容是："+share.getContent());
        return "redirect:index";//需要跳转至动态首页控制器
    }

}