package com.example.demo.config;

import com.example.demo.config.props.MultipleMongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.example.demo.repository.primary", mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig
{
}
