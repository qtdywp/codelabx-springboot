package com.example.demo.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig
{
    // 定义队列
    @Bean
    public Queue helloQueue()
    {
        return new Queue("hello");
    }

    // 定义队列
    @Bean
    public Queue objectQueue()
    {
        return new Queue("object");
    }
}
