package com.example.demo.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

@Component
public class Producer
{
    @Autowired
    // JmsMessagingTemplate是Spring提供发送消息的工具类
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    public void sendQueue(String msg)
    {
        System.out.println("Send queue msg :" + msg);
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }

    public void sendTopic(String msg)
    {
        System.out.println("Send topic msg :" + msg);
        this.jmsMessagingTemplate.convertAndSend(this.topic, msg);
    }
}
