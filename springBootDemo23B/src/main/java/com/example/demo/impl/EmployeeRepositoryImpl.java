package com.example.demo.impl;

import com.example.demo.EmployeeRepository;
import com.example.demo.config.DataSourceBox;
import com.example.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository
{
    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private JdbcTemplate secondaryJdbcTemplate;

    // 指定当前数据源默认为primaryJdbcTemplate
    @Resource(name = "primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplateNow;

    @Override
    public void changeJdbcTemplate(DataSourceBox dataSourceBox)
    {
        switch (dataSourceBox)
        {
            case MySQL_TEST1:
            {
                this.jdbcTemplateNow = primaryJdbcTemplate;
                break;
            }
            case MySQL_TEST2:
            {
                this.jdbcTemplateNow = secondaryJdbcTemplate;
                break;
            }
        }
    }

    @Override
    public int save(Employee employee)
    {
        return jdbcTemplateNow.update("INSERT INTO Employee (user_name, pass_word, login_time, remark) VALUES (?, ?, ?, ?);", employee.getUserName(), employee.getPassword(), employee.getLoginTime(), employee.getRemark());
    }

    @Override
    public int update(Employee employee)
    {
        return jdbcTemplateNow.update("UPDATE Employee SET user_name = ?, pass_word = ?, login_time = ?, remark = ? WHERE id=?", employee.getUserName(), employee.getPassword(), employee.getLoginTime(), employee.getRemark(), employee.getId());
    }

    @Override
    public int delete(long id)
    {
        return jdbcTemplateNow.update("DELETE FROM Employee where id = ? ", id);

    }

    @Override
    public Employee findById(long id)
    {
        // new BeanPropertyRowMapper<>是默认的数据库查询结果集与Model类之间的映射类，如果数据库字段名与Model属性名不一致（或者不符合驼峰命名法）则无法正常映射。
        // return jdbcTemplate.queryForObject("SELECT * FROM Employee WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Employee.class));
        return jdbcTemplateNow.queryForObject("SELECT * FROM Employee WHERE id=?", new Object[]{id}, new EmployeeRepositoryImpl.EmployeeRowMapper());
    }

    @Override
    public List<Employee> findALL()
    {
        return jdbcTemplateNow.query("SELECT * FROM Employee", new EmployeeRepositoryImpl.EmployeeRowMapper());
    }

    // 自定义数据库查询结果集与Model类之间的映射关系类
    class EmployeeRowMapper implements RowMapper<Employee>
    {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            Employee user = new Employee();
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("user_name"));
            user.setPassword(rs.getString("pass_word"));
            user.setLoginTime(rs.getTimestamp("login_time"));
            user.setRemark(rs.getString("remark"));
            return user;
        }
    }
}