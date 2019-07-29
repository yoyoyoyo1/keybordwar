package com.oracle.demo.controller;

import com.oracle.demo.entity.Likes;
import com.oracle.demo.entity.Message;
import com.oracle.demo.entity.User;
import com.oracle.demo.respository.DialogDao;
import com.oracle.demo.respository.LikeDao;
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
import java.util.*;

@RestController
@RequestMapping("/dialog")
public class Dialog {
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;
    @Autowired
    DialogService dialogService;
    @Autowired
    LikeDao likeDao;
    @Autowired
    DialogDao dialogDao;
    @GetMapping("/hot/spot")
    public List<com.oracle.demo.entity.Dialog> hotSpot(){
        return dialogDao.hotSpotDialog();
    }
    @GetMapping("/like")
    public Object like(HttpSession session,int l,int dialogId){
        int userId = ((User)session.getAttribute("user")).getId();
        com.oracle.demo.entity.Dialog dialog = dialogDao.findById(dialogId).get();

        if(l==1){
            likeDao.deleteDialogLike(userId,dialogId);
            dialogDao.updateLikes(dialogId,dialog.getLikes()-1);
        }else{
            Likes likes = new Likes();
            likes.setDialogId(dialogId);
            likes.setUserId(userId);
            likeDao.save(likes);
            dialogDao.updateLikes(dialogId,dialog.getLikes()+1);
        }
        return 1;
    }
    @GetMapping("/live/user")
    public Object liveUser(Integer dialogId){
        Map<Integer,Session> dialog =  dialogWebSocket.Dialog.get(dialogId);
        if(dialog==null||dialog.size()==0){
            return new ArrayList<>();
        }
        return  userService.findByIds(new ArrayList<>(dialog.keySet()));
    }

    @GetMapping("/history/user")
    public Object historyUser(Integer dialogId){
        List<Integer> userList = messageService.getFormIdByDialogId(dialogId);
        if(userList.size()!=0){
            return userService.findByIds(userList);
        }else {
            return new  ArrayList<>();
        }
    }
    @GetMapping("/message")
    public Object message(Integer dialogId,int page){
        return messageService.getMessageByDialogId(dialogId,page*10);
    }

    @GetMapping("/live")
    public List<com.oracle.demo.entity.Dialog> liveDialog(int page){
            return dialogService.getDialogs(1,page);
    }
    @GetMapping("/history")
    public Map<String,Object> historyDialog(HttpSession session,int page){

        List<Integer> dialogIds = new ArrayList<>();
        Map<String,Object> result  = new HashMap<>();
        List<com.oracle.demo.entity.Dialog> dialogs = dialogService.getDialogs(0,page);
        for(com.oracle.demo.entity.Dialog key : dialogs){
            dialogIds.add(key.getId());
        }
        result.put("dialogs",dialogs);
        result.put("like",likeDao.getLikesByDialogId(((User)session.getAttribute("user")).getId(),dialogIds));
        return result;
    }

}
