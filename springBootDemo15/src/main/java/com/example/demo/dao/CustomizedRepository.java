package com.example.demo.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CustomizedRepository
{
    List<LinkedHashMap<String, Object>> myBatisSelectSQL(@Param("sql") String sql);

    int myBatisUpdateSQL(@Param("sql") String sql);
}
