package com.example.demo;

import com.example.demo.scheduler.CronSchedulerJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyStartupRunner implements CommandLineRunner
{
    public CronSchedulerJob scheduleJobs;

    @Autowired
    public void setScheduleJobs(CronSchedulerJob scheduleJobs)
    {
        this.scheduleJobs = scheduleJobs;
    }

    @Override
    public void run(String... args) throws Exception
    {
        scheduleJobs.startSchedule();
        System.out.println(">>>>>>>>>>>>>>>定时任务开始执行<<<<<<<<<<<<<");
    }
}