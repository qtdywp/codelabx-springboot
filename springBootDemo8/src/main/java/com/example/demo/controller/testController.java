package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class testController
{
    @RequestMapping("/index")
    public String testPage(String name, int age)
    {
        return "name=" + name + ", age=" + age;
    }

    @RequestMapping("/index/{name2}/{age2}")
    public String testPage2(@PathVariable String name2, @PathVariable int age2)
    {
        return "name2=" + name2 + ", age2=" + age2;
    }

    @RequestMapping("/getUser")
    public User testPage3(User user)
    {
        return user;
    }

    @RequestMapping("/*/admin")
    public String testPage4()
    {
        return "测试*匹配";
    }

    @RequestMapping("/test5")
    public String testPage5(HttpServletRequest request, HttpServletResponse response)
    {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        return "name5=" + name + ", age5=" + age;
    }

    @RequestMapping(value = "/test6", method = RequestMethod.GET)
    public String testPage6()
    {
        return "GET请求成功！";
    }

    @RequestMapping(value = "/test7", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String testPage7()
    {
        return "POST请求成功！";
    }

    @RequestMapping(value = "/test8", consumes="application/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String testPage8()
    {
        return "application/json请求成功！";
    }

    @RequestMapping(value = "/test9", params="myParam=myValue", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String testPage9()
    {
        return "myParam=myValue请求成功！";
    }
}
