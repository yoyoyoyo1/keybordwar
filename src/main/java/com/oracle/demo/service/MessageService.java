package com.oracle.demo.service;


import com.oracle.demo.entity.Message;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface MessageService {
    public Object getMessageByDialogId(Integer dialogId,int page);
    public Object save(Message message);
    public Message sendMessage(Message message, HttpServletResponse response) throws IOException;
    public List<Integer> getFormIdByDialogId(Integer dialogId);
    public Object getchatMessageById(Integer formId,Integer toId);
    public List<Integer> latelyTalk(Integer userId,Long time);
    public List<Integer> eachOtherFollow(Integer userId);

}