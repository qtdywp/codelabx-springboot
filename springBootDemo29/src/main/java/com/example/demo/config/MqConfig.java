package com.example.demo.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class MqConfig
{
    @Bean
    public Queue queue()
    {
        // 定义一个Queue，实例化为ActiveMQQueue类型
        return new ActiveMQQueue("com.queue");
    }

    @Bean
    public Topic topic()
    {
        // 定义一个Topic，实例化为ActiveMQTopic类型
        return new ActiveMQTopic("com.topic");
    }
}