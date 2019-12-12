package com.example.demo.rabbit.object;

import com.example.demo.Model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectSender
{
    private AmqpTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(AmqpTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(User user)
    {
        System.out.println("Sender object: " + user.toString());
        this.rabbitTemplate.convertAndSend("object", user);
    }
}