package com.example.demo;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class DemoApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DemoApplication.class, args);
    }
}
