package com.oracle.demo.service;


import com.oracle.demo.entity.Message;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public interface MessageService {
    public Object getDialogMessageByIdAndTime(Integer dialogId,Long time);
    public Object save(Message message);
    public Message sendMessage(Message message, HttpServletResponse response) throws IOException;
}