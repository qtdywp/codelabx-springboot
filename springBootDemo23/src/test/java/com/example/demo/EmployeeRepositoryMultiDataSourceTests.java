package com.example.demo;

import com.example.demo.impl.EmployeeRepositoryMultiDataSourceImpl;
import com.example.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryMultiDataSourceTests
{
    @Autowired
    private EmployeeRepositoryMultiDataSourceImpl employeeRepository_MultiDataSourceImpl;

    @Test
    public void testSave()
    {
        Employee user = new Employee();
        user.setUserName("A01");
        user.setPassword("123456");
        user.setLoginTime(new Date());
        user.setRemark("备注01");
        employeeRepository_MultiDataSourceImpl.save(user);
    }
}
