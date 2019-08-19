package com.example.demo.dao.mapper;

import java.util.LinkedHashMap;
import java.util.List;

import com.example.demo.dao.entity.EmployeeEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EmployeeMapper
{
    // #{}中的名称要和Entity类中的属性名一致
    @Insert("INSERT INTO Employee(user_name, pass_word, login_time, remark) VALUES(#{userName}, #{password}, #{loginTime}, #{remark})")
    void insert(EmployeeEntity employeeEntity);

    // 由于数据库字段名和Entity中的属性不完全一致，因此这里需要编写映射字段。
    @Select("SELECT * FROM Employee WHERE user_name = '#{userName}'")
    @Results(id = "EmployeeResults", value = {@Result(column = "user_name", property = "userName"), @Result(column = "pass_word", property = "password"), @Result(column = "login_time", property = "loginTime")})
    // remark的映射就不用写了，因为数据库列名称和Entity类字段完全一样。
    EmployeeEntity getEmployeeByUserName(String userName);

    // 直接使用定义好的@Results
    @Select("SELECT * FROM Employee")
    @ResultMap("EmployeeResults")
    List<EmployeeEntity> getAll();

    // 不用Entity类接收返回值(省略写@Results的麻烦)
    @Select("SELECT * FROM Employee")
    List<LinkedHashMap<String, Object>> getAll_NoEntity();

    // 注意这里用$号(相当于拼接字符串)
    @Update("UPDATE Employee SET remark='${remark}' WHERE user_name = '${userName}'")
    void update(@Param("userName") String userName, @Param("remark") String remark);

    // 注意这里用#号（相当于？号传参）
    @Delete("DELETE FROM Employee WHERE ID=#{id}")
    void delete(Long id);

    @Delete("DELETE FROM Employee WHERE user_name = '${userName}'")
    void deleteByUserName(@Param("userName") String userName);
}
