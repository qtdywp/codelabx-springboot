package com.example.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.demo.dao.MyBatis")// 这个也可以直接写到Spring Boot的启动类上
public class AppConfig
{
}
