package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 启用Spring Security
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/").permitAll() // 配置不验证权限的目录
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/content/**").access("hasRole('ADMIN') or hasRole('USER')")
                .anyRequest().authenticated() // 其他的请求需要验证权限
                .and().formLogin() // 设置登录页面（如果不设置.loginPage()，将使用默认登录页面）
                .loginPage("/login") // 使用自定义登录页面(如果注释掉此行，将使用默认登录页面)
                .permitAll()
                .and().logout().permitAll() // 允许访问退出登录路径/logout
                .and().csrf().ignoringAntMatchers("/logout"); // 忽略CSRF保护的路径
    }

    // 代码方式配置账号密码与权限
    // 如果application.properties和这里同时设置了账号密码与权限，则以这里设置的为准
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder()) // 设置密码加密方式
                .withUser("user") // 设置用户名
                .password(new BCryptPasswordEncoder().encode("888888")) // 设置密码(需与前边设置的加密方式一致)
                .roles("USER") // 设置权限角色
                .and()
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("ADMIN", "USER");
    }

}