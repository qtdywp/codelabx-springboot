package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebController
{
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User getUser()
    {
        User user = new User();
        user.setUserName("李雷");
        user.setAge(60);
        user.setRemark("Hello, I am LiLei");

        return user;
    }

    @RequestMapping(value = "/getUser2", method = RequestMethod.GET)
    public List<User> getUser2()
    {
        User user = new User();
        user.setUserName("李雷");
        user.setAge(60);
        user.setRemark("Hello, I am LiLei");

        User user2 = new User();
        user2.setUserName("韩梅梅");
        user2.setAge(50);
        user2.setRemark("Hello, I am Hanmeimei");

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user2);

        return list;
    }
}
