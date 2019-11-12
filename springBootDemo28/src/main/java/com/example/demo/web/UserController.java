package com.example.demo.web;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController
{
    @RequestMapping("/hello")
    @Cacheable(value = "helloCache")
    public String hello(String name)
    {
        System.out.println("没有走缓存！");
        return "hello " + name;
    }

    @RequestMapping("/condition")
    @Cacheable(value = "condition", condition = "#name.length() <= 4")
    public String condition(String name)
    {
        System.out.println("没有走缓存！");
        return "hello " + name;
    }

    @RequestMapping("/getUser")
    @Cacheable(value = "usersCache", key = "#nickname")
    public String getUser(String nickname)
    {
        System.out.println("执行了数据库操作");
        return nickname;
    }

    @RequestMapping("/getPutUser")
    @CachePut(value = "usersCache", key = "#nickname")
    public String getPutUser(String nickname)
    {
        System.out.println("执行了数据库操作");
        return nickname + "_new";
    }

    @RequestMapping("/allEntries")
    @CacheEvict(value = "usersCache", allEntries = true)
    public String allEntries(String nickname)
    {
        String msg = "执行了allEntries";
        System.out.println(msg);
        return msg;
    }

    @RequestMapping("/beforeInvocation")
    @CacheEvict(value = "usersCache", allEntries = true, beforeInvocation = true)
    public void beforeInvocation()
    {
        throw new RuntimeException("test beforeInvocation");
    }
}