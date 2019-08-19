package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
public class WelcomeController
{
    @GetMapping("/")
    public String welcome(Map<String, Object> model)
    {
        model.put("time", new Date());
        model.put("message", "hello world");
        return "welcome";
    }

    @GetMapping("/welcome2")
    public String welcome(HttpServletRequest request)
    {
        request.setAttribute("time", new Date());
        request.setAttribute("message", "hello world2");
        return "welcome";
    }
}
