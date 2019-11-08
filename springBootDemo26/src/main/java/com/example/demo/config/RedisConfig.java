package com.example.demo.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport
{
    @Bean
    public KeyGenerator keyGenerator()
    {
        // 自定义主键的生产策略KeyGenerator，默认使用参数名作为主键。
        return new KeyGenerator()
        {
            @Override
            public Object generate(Object target, Method method, Object... params)
            {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params)
                {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
}