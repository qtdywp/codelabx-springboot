package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class WebController
{
    @Value("${com.example.demo.name}")
    private String demoName;

    @RequestMapping("/testConfig")
    public String getConfig()
    {
        return demoName;
    }

}
