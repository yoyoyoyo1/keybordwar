package com.oracle.demo.service;

import com.oracle.demo.entity.Dialog;

import java.util.List;

public interface DialogService {
    public List<Dialog> getDialogsByActive(int active);
    public List<Dialog> getDialogs(int active,int page);
    public int updateActive(Integer dialogId);
    public int updateAll(List<Integer> ids);
}
