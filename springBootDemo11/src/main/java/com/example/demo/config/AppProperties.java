package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties
{
    @Value("${com.example.demo.title}")
    private String title;

    @Value("${com.example.demo.description}")
    private String description;
    @Value("${com.example.demo.keywords}")
    private String keywords;

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public String getKeywords()
    {
        return keywords;
    }
}
