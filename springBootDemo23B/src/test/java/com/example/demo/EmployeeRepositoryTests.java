package com.example.demo;

import com.example.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static com.example.demo.config.DataSourceBox.MySQL_TEST1;
import static com.example.demo.config.DataSourceBox.MySQL_TEST2;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTests
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSave()
    {
        Employee user = new Employee();
        user.setUserName("A01");
        user.setPassword("123456");
        user.setLoginTime(new Date());
        user.setRemark("备注01");
        employeeRepository.changeJdbcTemplate(MySQL_TEST1);
        employeeRepository.save(user);

        Employee user2 = new Employee();
        user2.setUserName("A02");
        user2.setPassword("12345678");
        user2.setLoginTime(new Date());
        user2.setRemark("备注02");
        employeeRepository.changeJdbcTemplate(MySQL_TEST2);
        employeeRepository.save(user2);
    }

    @Test
    public void testFindALL()
    {
        List<Employee> users = employeeRepository.findALL();
        for (Employee user : users)
        {
            System.out.println("user == " + user.toString());
        }
    }

    @Test
    public void testUpdate()
    {
        Employee user = new Employee();
        user.setId(40L); // 这个ID要改成和数据库里的数据ID一致
        user.setUserName("A01");
        user.setPassword("123456");
        user.setLoginTime(new Date());
        user.setRemark("备注01_new");
        employeeRepository.update(user);
    }

    @Test
    public void testFindById()
    {
        Employee user = employeeRepository.findById(28L); // 这个ID要改成和数据库里的数据ID一致
        System.out.println("user == " + user.toString());
    }

    @Test
    public void testDelete()
    {
        employeeRepository.delete(28L); // 这个ID要改成和数据库里的数据ID一致
    }
}