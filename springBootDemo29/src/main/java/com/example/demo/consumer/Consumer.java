package com.example.demo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer
{
    // @JmsListener用于定义一个JMS监听器，监听destination定义的名称的消息
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