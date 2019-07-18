package com.oracle.demo.service;

import com.oracle.demo.entity.Message;
import com.oracle.demo.entity.Share;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface MessageService {
    //发送一条
    public Message sendMessage(Message message, HttpServletResponse response) throws IOException;
    //List<Message> getAll();
    //void save(Message message);
}
