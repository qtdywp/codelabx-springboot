package com.example.demo.controller;

import com.example.demo.dao.entity.EmployeeEntity;
import com.example.demo.dao.mapper.EmployeeMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class EmployeeController
{
    @Resource
    private EmployeeMapper employeeMapper;

    @RequestMapping("/getEmployees")
    public void getUsers()
    {
        List<EmployeeEntity> employeeEntitys = employeeMapper.getAll();
        for (EmployeeEntity employeeEntity : employeeEntitys)
        {
            System.out.println(employeeEntity.getUserName());
            System.out.println(employeeEntity.getPassword());
            System.out.println(employeeEntity.getLoginTime());
            System.out.println(employeeEntity.getRemark());
            System.out.println("--------------");
        }
    }
}