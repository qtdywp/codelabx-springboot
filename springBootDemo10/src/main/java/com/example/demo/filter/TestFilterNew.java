package com.example.demo.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;

@Order(1) // 设置过滤优先级，值越小，越先执行(目前测试这个功能无效)
@WebFilter(filterName = "testFilterNew", urlPatterns = {"/*"}, initParams = {@WebInitParam(name = "paramNameNew", value = "paramValueNew")})
// 设置注册名称、匹配规则、初始化参数
public class TestFilterNew implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        // TODO Auto-generated method stub
        Filter.super.init(filterConfig);
        System.out.println(filterConfig.getInitParameter("paramNameNew"));
    }

    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain fChain) throws IOException, ServletException
    {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) sRequest;
        // 获取并打印出过滤到的RequestURI
        System.out.println("TestFilterNew URL:" + request.getRequestURI());
        // 最后要把请求加入到过滤链中
        fChain.doFilter(sRequest, sResponse);
    }
}
