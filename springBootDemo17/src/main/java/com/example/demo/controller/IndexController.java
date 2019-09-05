package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
    @RequestMapping("/welcome")
    public String toIndex(ModelMap map)
    {
        map.addAttribute("welcomeText", "Welcome to CodeLabX!");
        return "index";
    }
}
