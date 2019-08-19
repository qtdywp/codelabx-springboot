package com.example.demo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TestFilter2 implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        // TODO Auto-generated method stub
        Filter.super.init(filterConfig);
        // 在这里可以获取到FilterRegistrationBean里addInitParameter里设置的参数
        System.out.println(filterConfig.getInitParameter("paramName"));
    }

    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain fChain) throws IOException, ServletException
    {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) sRequest;
        // 获取并打印出过滤到的RequestURI
        System.out.println("TestFilter2 URL:" + request.getRequestURI());
        // 最后要把请求加入到过滤链中
        fChain.doFilter(sRequest, sResponse);
    }
}
