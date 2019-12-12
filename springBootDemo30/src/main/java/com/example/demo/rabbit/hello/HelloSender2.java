package com.example.demo.rabbit.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender2
{
    private AmqpTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(AmqpTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(int i)
    {
        String context = "Send2 hello " + i;
        System.out.println("Sender2: " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}