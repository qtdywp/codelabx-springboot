package com.example.demo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController
{
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/content")
    public String content()
    {
        return "content";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login()
    {
        return "login";
    }

    @RequestMapping("/admin")
    public String admin()
    {
        return "admin";
    }

    // 仅限ADMIN权限执行此方法
    // @PostAuthorize("hasAuthority('ADMIN')") 先执行方法，后验证权限，这个很少用
    // 注意在配置类要加上启用标签
    // @EnableGlobalMethodSecurity(prePostEnabled = true)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("/edit")
    public String adminPath()
    {
        return "edit";
    }

    // 注意在配置类要加上启用标签
    // @EnableGlobalMethodSecurity(securedEnabled = true)
    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/update")
    public String update()
    {
        return "update";
    }
}
