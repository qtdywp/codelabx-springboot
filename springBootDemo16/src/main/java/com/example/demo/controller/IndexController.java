package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController
{
    @RequestMapping("/")
    public String toIndex(ModelMap map)
    {
        map.addAttribute("welcomeText", "Welcome To CodeLabX!");

        return "index";
    }

    @RequestMapping("/inline")
    public String toInline(ModelMap map)
    {
        map.addAttribute("welcomeText", "Welcome To CodeLabX!");

        map.addAttribute("textInline1", "文本内联1");
        map.addAttribute("textInline2", "文本内联2");
        map.addAttribute("textInline3", "文本内联3");

        map.addAttribute("classname", "spanTextClass");
        map.addAttribute("align", "center");
        return "inline";
    }

    @RequestMapping("/basic")
    public String toBasic(HttpServletRequest request)
    {
        request.setAttribute("requestInfo", "request 信息");
        request.getSession().setAttribute("SiteName", "CodeLabX");

        return "basic";
    }

    @RequestMapping("/utility")
    public String utility(ModelMap map)
    {
        List<String> users = new ArrayList<>();
        users.add("111");
        users.add(null);
        users.add("333");

        map.addAttribute("userName", "CodeLabX");
        map.addAttribute("users", users);
        map.addAttribute("count", 12);
        map.addAttribute("date", new Date());
        return "utility";
    }

    @RequestMapping("/i18n")
    public String toI18n()
    {
        return "i18n";
    }

    @RequestMapping("/URL")
    public String toURL(ModelMap map)
    {
        map.addAttribute("productID", "996");
        map.addAttribute("colorID", "724");
        return "URL";
    }

    @RequestMapping("/layout")
    public String toLayout()
    {
        return "layout";
    }

    @RequestMapping("/book")
    public String toBook()
    {
        return "book";
    }
}
