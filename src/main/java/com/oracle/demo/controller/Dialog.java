package com.oracle.demo.controller;

import com.oracle.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dialog")
public class Dialog {
    @Autowired
    MessageService messageService;
    @GetMapping("/user")
    public Object user(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("id",3);
        map.put("nickname",123);
        map.put("motto","asd");
        list.add(map);
        return list;
    }

    @GetMapping("/message")
    public Object message(Integer dialogId,Long time){
        return messageService.getDialogMessageByIdAndTime(dialogId, time);
    }
}
