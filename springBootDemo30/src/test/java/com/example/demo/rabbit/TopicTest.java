package com.example.demo.rabbit;

import com.example.demo.rabbit.topic.TopicSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TopicTest
{
    @Autowired
    private TopicSender sender;

    @Test
    public void topic1() throws Exception
    {
        sender.send1();
        Thread.sleep(1000l);
    }

    @Test
    public void topic2() throws Exception
    {
        sender.send2();
        Thread.sleep(1000l);
    }
}
