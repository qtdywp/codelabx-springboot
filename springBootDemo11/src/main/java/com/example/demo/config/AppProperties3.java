package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "other")
@PropertySource("classpath:other.properties")
public class AppProperties3
{
    private String bj;
    private String sh;

    public String getBj()
    {
        return bj;
    }

    public void setBj(String bj)
    {
        this.bj = bj;
    }

    public String getSh()
    {
        return sh;
    }

    public void setSh(String sh)
    {
        this.sh = sh;
    }
}
