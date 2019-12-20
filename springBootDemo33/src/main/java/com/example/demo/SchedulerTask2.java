package com.example.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SchedulerTask2
{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 1000) // 上一次开始执行时间点之后1秒再执行
    public void fixedRateTest()
    {
        System.out.println("现在时间1：" + dateFormat.format(new Date()));
    }

    @Scheduled(fixedDelay = 1000) // 上一次执行完毕时间点之后1秒再执行
    public void fixedDelayTest() throws InterruptedException
    {
        Thread.sleep(2000);
        System.out.println("现在时间2：" + dateFormat.format(new Date()));
    }

    @Scheduled(initialDelay=500, fixedRate=2000) // 第一次延迟500毫秒后执行，之后按fixedRate的规则每2秒执行一次
    public void initialDelayTest()
    {
        System.out.println("现在时间3：" + dateFormat.format(new Date()));
    }
}
