package com.example.demo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer2
{
    @JmsListener(destination = "com.queue")
    public void receiveQueue(String text)
    {
        System.out.println("Consumer2 queue msg : " + text);
    }

    @JmsListener(destination = "com.topic")
    public void receiveTopic(String text)
    {
        System.out.println("Consumer2 topic msg : " + text);
    }
}