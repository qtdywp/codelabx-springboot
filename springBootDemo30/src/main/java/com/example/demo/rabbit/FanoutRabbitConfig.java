package com.example.demo.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FanoutRabbitConfig
{
    // 定义队列
    @Bean
    public Queue AMessage()
    {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage()
    {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage()
    {
        return new Queue("fanout.C");
    }

    // 定义交换机
    @Bean
    FanoutExchange fanoutExchange()
    {
        return new FanoutExchange("fanoutExchange");
    }

    // 将队列与交换机绑定
    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange)
    {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange)
    {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange)
    {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
}