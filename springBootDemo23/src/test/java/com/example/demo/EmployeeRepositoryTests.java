package com.example.demo;

import com.example.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

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
        employeeRepository.save(user);

        Employee user2 = new Employee();
        user2.setUserName("A02");
        user2.setPassword("12345678");
        user2.setLoginTime(new Date());
        user2.setRemark("备注02");
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
        user.setId(28L); // 这个ID要改成和数据库里的数据ID一致
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

    @Test
    public void testSave2()
    {
        Employee user3 = new Employee();
        user3.setUserName("A03");
        user3.setPassword("123456");
        user3.setLoginTime(new Date());
        user3.setRemark("备注03");
        employeeRepository.save(user3);

        Employee user4 = new Employee();
        user4.setUserName("A04");
        user4.setPassword("12345678");
        user4.setLoginTime(new Date());
        user4.setRemark("备注04");
        employeeRepository.save(user4, MySQL_TEST2);
    }

}