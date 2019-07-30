package com.oracle.demo.controller;

import com.alibaba.fastjson.JSON;
import com.oracle.demo.entity.Message;
import com.oracle.demo.entity.User;
import com.oracle.demo.service.UserService;
import com.oracle.demo.service.impl.MessageServiceImpl;
import com.oracle.demo.util.BeanToMap;
import com.oracle.demo.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/privatechat")
public class MessageController {
    @Autowired
    private MessageServiceImpl messageService;
    @Autowired
    private UserService userService;

    @GetMapping("/live/user")


    @RequestMapping("sendmessage")
    public String sendmessage(@ModelAttribute Message message, HttpServletResponse response) throws IOException
    {
        messageService.sendMessage(message,response);

        return "redirect:tomessage";//需要跳转至动态首页控制器
    }

    @GetMapping("history/message")
    @ResponseBody
    public Object message(Integer formId,Integer toId)
    {
      return messageService.getchatMessageById(formId,toId);
    }
    @GetMapping("/user/latelyTalk")
    @ResponseBody
    public List<User> latelyTalk(HttpSession session){
        Map<String, Object> user = BeanToMap.BeanToMap(session.getAttribute("user"));
        List<Integer> a = messageService.latelyTalk((Integer) user.get("id"),((new Date()).getTime()/1000)-60*24*30);
        if(a.size()!=0){
            return userService.findByIds(a);
        }else {
            return new  ArrayList<>();
        }

    }
    @GetMapping("/user/eachother")
    @ResponseBody
    public  List<User> eachother(HttpSession session){
        List<User> userList = new ArrayList<>();
        Map<String, Object> user = BeanToMap.BeanToMap(session.getAttribute("user"));
        List<Integer> a = messageService.eachOtherFollow((Integer) user.get("id"));
        if(a.size()!=0){
            return userService.findByIds(a);
        }else {
            return new  ArrayList<>();
        }
    }

}
