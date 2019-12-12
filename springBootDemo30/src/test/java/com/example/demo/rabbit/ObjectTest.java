package com.example.demo.rabbit;

import com.example.demo.Model.User;
import com.example.demo.rabbit.object.ObjectSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectTest
{
    @Autowired
    private ObjectSender objectSender;

    @Test
    void sendObject() throws Exception
    {
        User user = new User();
        user.setUserName("用户名");
        user.setPassWord("12345678");

        objectSender.send(user);
        Thread.sleep(1000L);
    }
}
