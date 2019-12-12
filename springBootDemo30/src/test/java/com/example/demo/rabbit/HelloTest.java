package com.example.demo.rabbit;

import com.example.demo.rabbit.hello.HelloSender;
import com.example.demo.rabbit.hello.HelloSender2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloTest
{
    @Autowired
    HelloSender helloSender;

    @Autowired
    HelloSender2 helloSender2;

    @Test
    void hello() throws Exception
    {
        helloSender.send();
        Thread.sleep(1000L);
    }

    @Test
    void oneToMany() throws Exception
    {
        for (int i = 0; i < 30; i++)
        {
            helloSender.send(i);
        }
        Thread.sleep(1000L);
    }

    @Test
    void manyToMany() throws Exception
    {
        for (int i = 0; i < 30; i++)
        {
            helloSender.send(i);
            helloSender2.send(i);
        }
        Thread.sleep(1000L);
    }
}