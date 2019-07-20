package com.oracle.demo.controller;

import com.oracle.demo.entity.Message;
import com.oracle.demo.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/privatechat")
public class MessageController {
    @Autowired
    private MessageServiceImpl messageService;

    @GetMapping("/friend")
    public Object friend(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("id",2);
        map.put("nickname",123);
        list.add(map);
        return list;
    }
//    @RequestMapping("/index")
//    public String toIndex(Model model){
//        List<Share> shareList=shareService.getAll();
//        model.addAttribute("shareList",shareList);
//        return "index";
//    }

  /*  @RequestMapping("sendmessage")
      public String sendmessage(@ModelAttribute Message message, HttpServletResponse response) throws IOException
      {
        messageService.sendMessage(message,response);

        return "redirect:message";//需要跳转至动态首页控制器
      }*/
    @GetMapping ("/message")
    public Object message(Integer toId,Long time)
    {

       return messageService.getchatMessageByIdAndTime(toId,time);


    }

}
