package com.example.demo.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TopicRabbitConfig
{
    // 定义队列
    @Bean
    public Queue queueMessage()
    {
        return new Queue("topic.message");
    }

    @Bean
    public Queue queueMessages()
    {
        return new Queue("topic.messages");
    }

    // 定义交换机
    @Bean
    TopicExchange exchange()
    {
        return new TopicExchange("topicExchange");
    }

    // 将队列与交换机绑定
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange)
    {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.msg");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange)
    {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
