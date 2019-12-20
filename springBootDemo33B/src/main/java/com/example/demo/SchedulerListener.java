package com.example.demo;

import com.example.demo.scheduler.CronSchedulerJob;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
@Component
public class SchedulerListener
{
    public CronSchedulerJob scheduleJobs;

    @Autowired
    public void setScheduleJobs(CronSchedulerJob scheduleJobs)
    {
        this.scheduleJobs = scheduleJobs;
    }

    @Scheduled(cron = "0 53 15 * * *") // 每天的15:53分开始执行
    public void schedule() throws SchedulerException
    {
        scheduleJobs.startSchedule();
    }
}