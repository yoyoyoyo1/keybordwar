package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Message;
import com.oracle.demo.respository.MessageDao;
import com.oracle.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    public MessageDao messageDao;
    @Override
    public Object getMessageByDialogId(Integer dialogId) {
        return messageDao.getDialogMessageByIdAndTime(dialogId);
    }

    @Override
    public Object save(Message message) {
        return messageDao.save(message);
    }
    
    public Message sendMessage(Message message, HttpServletResponse response) throws IOException {
        /*PrintWriter out=response.getWriter();
        out.print("<html><head><meta charset='UTF-8'></head>");
        out.print("<script>alert('发布成功');</script>");
        out.flush();
        out.close();*/
        return null;
    }

    @Override
    public List<Integer> getFormIdByDialogId(Integer dialogId) {
        return messageDao.getFormIdByDialogId(dialogId);
    }
}
