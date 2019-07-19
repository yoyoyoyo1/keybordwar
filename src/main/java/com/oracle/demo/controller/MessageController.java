package com.oracle.demo.controller;

import com.oracle.demo.entity.Message;
import com.oracle.demo.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Controller
public class MessageController {
    @Autowired
    private MessageServiceImpl messageService;


//    @RequestMapping("/index")
//    public String toIndex(Model model){
//        List<Share> shareList=shareService.getAll();
//        model.addAttribute("shareList",shareList);
//        return "index";
//    }

    @RequestMapping("sendmessage")
    public String sendmessage(@ModelAttribute Message message, HttpServletResponse response) throws IOException
    {
        messageService.sendMessage(message,response);

        return "redirect:tomessage";//需要跳转至动态首页控制器
    }
    @RequestMapping("tomessage")
    public String tomessage()
    {
        return "messages";
    }
}
