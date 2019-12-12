package com.example.demo;

import com.example.demo.producer.Producer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest
class ActiveMqTests
{
    @Autowired
    private Producer producer;

    @Test
    void sendQueueMessage(CapturedOutput output) throws InterruptedException
    {
        this.producer.sendQueue("Test queue message");
        Thread.sleep(1000L);
        assertThat(output).contains("Test queue");
    }

    @Test
    void sendQueueMessage2()
    {
        for (int i = 0; i < 100; i++)
        {
            this.producer.sendQueue("Test queue message" + i);
        }
    }

    @Test
    void sendTopicMessage()
    {
        this.producer.sendTopic("Test Topic message");
    }
}