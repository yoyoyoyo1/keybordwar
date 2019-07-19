package com.oracle.demo.service;

import com.oracle.demo.entity.Dialog;

import java.util.List;

public interface DialogService {
    public List<Dialog> getDialogsByActive(int i);
}
