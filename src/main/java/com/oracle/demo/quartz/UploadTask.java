package com.oracle.demo.quartz;

import com.oracle.demo.entity.Dialog;
import com.oracle.demo.service.DialogService;
import com.oracle.demo.service.MessageService;
import com.oracle.demo.websocket.dialogWebSocket;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.*;


public class UploadTask extends QuartzJobBean {
    @Autowired
    DialogService dialogService;
    @Autowired
    MessageService messageService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务开始");
        try {
            List<Dialog> dialogList = dialogService.getDialogsByActive(1);
            List<Integer> ids = new ArrayList<>();
            for(Dialog dialog : dialogList){
                ids.add(dialog.getId());
            }
            if(ids.size()!=0){
                ids = messageService.getLazyDialogId(ids,(new Date().getTime())/1000-60*60);
                if(ids != null && ids.size() != 0){
                    for ( Integer dialogId: ids){
                        for (Integer key : dialogWebSocket.Dialog.get(dialogId).keySet()) {
                            dialogWebSocket.Dialog.get(dialogId).get(key).close();
                        }
                        dialogWebSocket.Dialog.remove(dialogId);
                        dialogWebSocket.dialogs.remove(dialogId);
                    }
                    dialogService.updateAll(ids);
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("任务结束");
    }
}