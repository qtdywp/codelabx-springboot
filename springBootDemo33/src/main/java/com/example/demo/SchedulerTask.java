package com.example.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask
{
    private int count = 0;

    @Scheduled(cron = "*/3 * * * * ?") // 每隔3秒执行一次
    private void cronTest()
    {
        System.out.println("This scheduler task is running at " + (count++));
    }
}