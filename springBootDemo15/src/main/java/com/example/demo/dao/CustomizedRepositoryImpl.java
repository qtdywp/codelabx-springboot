package com.example.demo.dao;

import com.example.demo.dao.MyBatis.MyBatisMapper;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

// 在这个实现类里用你自己喜欢的DAO框架，比如说MyBatis, JdbcTemplate
public class CustomizedRepositoryImpl implements CustomizedRepository
{
    @Resource
    MyBatisMapper employeeMapper;

    @Override
    public List<LinkedHashMap<String, Object>> myBatisSelectSQL(String sql)
    {
        return employeeMapper.myBatisSelectSQL(sql);
    }

    @Override
    public int myBatisUpdateSQL(String sql)
    {
        return employeeMapper.myBatisUpdateSQL(sql);
    }
}
