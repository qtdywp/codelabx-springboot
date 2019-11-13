package com.example.demo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer
{
    @JmsListener(destination = "com.queue")
    public void receiveQueue(String text)
    {
        System.out.println("Consumer queue msg : " + text);
    }

    @JmsListener(destination = "com.topic")
    public void receiveTopic(String text)
    {
        System.out.println("Consumer topic msg : " + text);
    }
}