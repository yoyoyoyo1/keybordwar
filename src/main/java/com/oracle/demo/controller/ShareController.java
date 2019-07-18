package com.oracle.demo.controller;


import com.oracle.demo.entity.Share;
import com.oracle.demo.service.impl.ShareServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
@Controller
public class ShareController {
    @Autowired
    private ShareServiceImpl shareService;


    @RequestMapping("/index")
    public String toIndex(Model model){
        List<Share> shareList=shareService.getAll();
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