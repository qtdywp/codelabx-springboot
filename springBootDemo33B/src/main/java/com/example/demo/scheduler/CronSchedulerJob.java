package com.example.demo.scheduler;

import com.example.demo.job.ScheduledJob;
import com.example.demo.job.ScheduledJob2;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class CronSchedulerJob
{
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    public void setSchedulerFactoryBean(SchedulerFactoryBean schedulerFactoryBean)
    {
        this.schedulerFactoryBean = schedulerFactoryBean;
    }

    public void startSchedule() throws SchedulerException
    {
        // 通过工厂类获取Scheduler
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        // 为Scheduler设置JobDetail与CronTrigger
        scheduleJob1(scheduler);
        scheduleJob2(scheduler);
    }

    private void scheduleJob1(Scheduler scheduler) throws SchedulerException
    {
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class).withIdentity("job1", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    private void scheduleJob2(Scheduler scheduler) throws SchedulerException
    {
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob2.class).withIdentity("job2", "group2").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }
}