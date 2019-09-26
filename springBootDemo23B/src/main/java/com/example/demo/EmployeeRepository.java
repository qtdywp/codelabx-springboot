package com.example.demo;

import com.example.demo.config.DataSourceBox;
import com.example.model.Employee;

import java.util.List;

public interface EmployeeRepository
{
    void changeJdbcTemplate(DataSourceBox dataSourceBox);

    int save(Employee user);

    int update(Employee user);

    int delete(long id);

    List<Employee> findALL();

    Employee findById(long id);
}