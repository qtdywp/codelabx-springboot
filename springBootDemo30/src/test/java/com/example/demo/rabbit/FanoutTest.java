package com.example.demo.rabbit;

import com.example.demo.rabbit.fanout.FanoutSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FanoutTest
{
    @Autowired
    private FanoutSender sender;

    @Test
    public void fanoutSender() throws Exception
    {
        sender.send();
        Thread.sleep(1000l);
    }
}