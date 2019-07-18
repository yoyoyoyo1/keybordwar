package com.oracle.demo.service;


import com.oracle.demo.entity.Message;

import java.util.Date;

public interface MessageService {
    public Object getDialogMessageByIdAndTime(Integer dialogId,Long time);
    public Object save(Message message);
}
