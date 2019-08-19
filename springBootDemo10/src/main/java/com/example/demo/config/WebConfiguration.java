package com.example.demo.config;

import com.example.demo.filter.TestFilter;
import com.example.demo.filter.TestFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration
{
    @Bean
    public FilterRegistrationBean<TestFilter> testFilterRegistration()
    {
        // 通过FilterRegistrationBean类把自定义Filter添加到Servlet 3.0+容器中
        FilterRegistrationBean<TestFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new TestFilter()); // 设置自定义Filter
        registration.addUrlPatterns("/*"); // 设置匹配规则
        registration.addInitParameter("paramName", "paramValue"); // 在这里给自定义Filter传递初始化参数
        registration.setName("TestFilter"); // 设置注册名称，如果不设置此项，则默认以自定义Filter类的名称做为注册名称
        registration.setOrder(1); // 设置过滤优先级，值越小，越先执行
        return registration;
    }

    @Bean
    public FilterRegistrationBean<TestFilter2> testFilterRegistration2()
    {
        // 通过FilterRegistrationBean类把自定义Filter添加到Servlet 3.0+容器中
        FilterRegistrationBean<TestFilter2> registration = new FilterRegistrationBean<>();
        registration.setFilter(new TestFilter2()); // 设置自定义Filter
        registration.addUrlPatterns("/*"); // 设置匹配规则
        registration.addInitParameter("paramName", "paramValue2"); // 在这里给自定义Filter传递初始化参数
        registration.setName("TestFilter2"); // 设置注册名称，如果不设置此项，则默认以自定义Filter类的名称做为注册名称
        registration.setOrder(2); // 设置过滤优先级，值越小，越先执行
        return registration;
    }

}
