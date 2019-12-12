package com.example.demo.config;

import com.example.demo.config.props.MultipleMongoProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MultipleMongoConfig
{
    private MultipleMongoProperties mongoProperties;

    @Autowired
    public void setMongoProperties(MultipleMongoProperties mongoProperties)
    {
        this.mongoProperties = mongoProperties;
    }

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() throws Exception
    {
        return new MongoTemplate(primaryFactory(this.mongoProperties.getPrimary()));
    }

    @Qualifier("secondaryMongoTemplate")
    @Bean
    public MongoTemplate secondaryMongoTemplate() throws Exception
    {
        return new MongoTemplate(secondaryFactory(this.mongoProperties.getSecondary()));
    }

    @Primary
    @Bean
    public MongoDbFactory primaryFactory(MongoProperties mongo) throws Exception
    {
        MongoClient client = new MongoClient(new MongoClientURI(mongoProperties.getPrimary().getUri()));
        return new SimpleMongoDbFactory(client, mongoProperties.getPrimary().getDatabase());
    }

    @Bean
    public MongoDbFactory secondaryFactory(MongoProperties mongo) throws Exception
    {
        MongoClient client = new MongoClient(new MongoClientURI(mongoProperties.getSecondary().getUri()));
        return new SimpleMongoDbFactory(client, mongoProperties.getSecondary().getDatabase());
    }
}