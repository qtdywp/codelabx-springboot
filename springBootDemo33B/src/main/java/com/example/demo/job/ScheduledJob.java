package com.example.demo.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScheduledJob implements Job
{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        System.out.println("Scheduled Job1 is running ...");
    }
}