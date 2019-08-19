package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
    //@RequestMapping("/index")
    @RequestMapping("/welcome")
    public String indexPage(ModelMap map, String name)
    {
        map.addAttribute("welcomeText", "Welcome to CodeLabX!" + name);
        return "index";
    }
}
