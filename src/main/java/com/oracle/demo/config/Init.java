package com.oracle.demo.config;

import com.oracle.demo.entity.Dialog;
import com.oracle.demo.respository.InfoDao;
import com.oracle.demo.service.DialogService;
import com.oracle.demo.websocket.dialogWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Component
public class Init implements ApplicationRunner {

    @Autowired
    public DialogService dialogService;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Dialog> dialogs = dialogService.getDialogsByActive(1);
        System.out.println(dialogs);
        for (Dialog dialog : dialogs){
            dialogWebSocket.Dialog.put(dialog.getId(),new HashMap<>());
            dialogWebSocket.dialogs.put(dialog.getId(),dialog);
        }
    }
}
