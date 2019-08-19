package com.example.demo;

import com.example.demo.config.AppProperties;
import com.example.demo.config.AppProperties2;
import com.example.demo.config.AppProperties3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigTest
{
    @Resource
    AppProperties appProperties;

    @Resource
    AppProperties2 appProperties2;

    @Resource
    AppProperties3 appProperties3;

    @Value("${com.example.demo.keywords}")
    private String keywords;

    @Test
    public void testProperties()
    {
        // 直接获取@Value注入的值
        System.out.println(keywords);

        // 通过从Spring管理的类中获取配置文件类AppProperties实例，进而获取配置项的值
        System.out.println(appProperties.getTitle());
        System.out.println(appProperties.getDescription());
    }

    @Test
    public void testProperties2()
    {
        System.out.println(appProperties2.getBj());
        System.out.println(appProperties2.getSh());

        System.out.println(appProperties2.getProvince().getHb());
        System.out.println(appProperties2.getProvince().getSd());

    }

    @Test
    public void testProperties3()
    {
        System.out.println(appProperties3.getBj());
        System.out.println(appProperties3.getSh());
    }


}
