package com.example.demo.rabbit.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender
{
    private AmqpTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(AmqpTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send()
    {
        String context = "Send hello " + new Date();
        System.out.println("Sender: " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

    public void send(int i)
    {
        String context = "Send hello " + i;
        System.out.println("Sender: " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}