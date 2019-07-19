package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Dialog;
import com.oracle.demo.respository.DialogDao;
import com.oracle.demo.service.DialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DialogServiceImpl implements DialogService {
    @Autowired
    DialogDao dialogDao;
    @Override
    public List<Dialog> getDialogsByActive(int i) {
        return dialogDao.getDialogsByActive(i);
    }
}
