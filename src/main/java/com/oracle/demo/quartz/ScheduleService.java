package com.oracle.demo.quartz;


import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ScheduleService {
    @Autowired
    private Scheduler scheduler;
    public void testScheduleTask() throws SchedulerException {
            JobDetail jobDetail = JobBuilder.newJob(UploadTask.class)
                    .build();
            //cron表达式 表示每隔i秒执行
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                    .cronSchedule("0 0 * * * ?")
                    .withMisfireHandlingInstructionDoNothing();

            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withSchedule(scheduleBuilder)
                    .startNow().build();

            scheduler.scheduleJob(jobDetail,cronTrigger);
    }
}
