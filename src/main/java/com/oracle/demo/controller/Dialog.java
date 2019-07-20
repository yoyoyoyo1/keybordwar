package com.oracle.demo.controller;

import com.oracle.demo.entity.Message;
import com.oracle.demo.entity.User;
import com.oracle.demo.service.DialogService;
import com.oracle.demo.service.MessageService;
import com.oracle.demo.service.UserService;
import com.oracle.demo.websocket.dialogWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
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
    @Autowired
    UserService userService;
    @Autowired
    DialogService dialogService;
    @GetMapping("/live/user")
    public Object liveUser(String dialogId){
        List<User> list = new ArrayList<>();
        System.out.println(dialogWebSocket.Dialog);
        Map<String,Session> dialog =  dialogWebSocket.Dialog.get(dialogId);
        if(dialog==null){
            return list;
        }
        for (String key : dialog.keySet()) {
            list.add(userService.findById(Integer.parseInt(key)));
        }
        return list;
    }

    @GetMapping("/history/user")
    public Object historyUser(Integer dialogId){
        List<User> list = new ArrayList<>();
        List<Integer> userList = messageService.getFormIdByDialogId(dialogId);
        for(Integer key : userList){
            list.add(userService.findById(key));
        }

        return list;
    }
    @GetMapping("/message")
    public Object message(Integer dialogId,Long time){
        return messageService.getMessageByDialogId(dialogId);
    }

    @GetMapping("/live")
    public List<com.oracle.demo.entity.Dialog> liveDialog(){
        return dialogService.getDialogsByActive(1);
    }

}
