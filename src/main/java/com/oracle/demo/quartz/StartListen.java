package com.oracle.demo.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartListen implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ScheduleService scheduleService;
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {
            scheduleService.testScheduleTask();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
