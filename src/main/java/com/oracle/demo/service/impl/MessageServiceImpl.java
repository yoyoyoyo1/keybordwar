package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Message;
import com.oracle.demo.respository.MessageDao;
import com.oracle.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    public MessageDao messageDao;
    @Override
    public Object getDialogMessageByIdAndTime(Integer dialogId, Long time) {
        return messageDao.getDialogMessageByIdAndTime(dialogId, time);
    }

    @Override
    public Object save(Message message) {
        return messageDao.save(message);
    }
}
