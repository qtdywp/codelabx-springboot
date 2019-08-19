package com.example.demo;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.example.demo.dao.mapper.EmployeeMapper;
import com.example.demo.dao.entity.EmployeeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest
{
    @Resource
    EmployeeMapper employeeMapper;

    @Test
    public void TestInsert()
    {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setUserName("wang");
        employeeEntity.setPassword("123456");
        employeeEntity.setLoginTime(new Date());
        employeeEntity.setRemark("备注XXX");
        employeeMapper.insert(employeeEntity);

        EmployeeEntity employeeEntity2 = new EmployeeEntity();
        employeeEntity2.setUserName("wang2");
        employeeEntity2.setPassword("888888");
        employeeEntity2.setLoginTime(new Date());
        employeeEntity2.setRemark("备注YYY");
        employeeMapper.insert(employeeEntity2);
    }

    @Test
    public void testSelect()
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

    @Test
    public void testSelectNoEntity()
    {
        List<LinkedHashMap<String, Object>> employeeEntitys = employeeMapper.getAll_NoEntity();
        for (LinkedHashMap<String, Object> hashMap : employeeEntitys)
        {
            for (String key : hashMap.keySet())
            {
                System.out.println(key + ":" + hashMap.get(key));
            }
            System.out.println("--------------");
        }
    }

    @Test
    public void testUpdate() throws Exception
    {
        employeeMapper.update("wang2", "KKKKKKKKK");
    }

    @Test
    public void testDelete() throws Exception
    {
        employeeMapper.deleteByUserName("wang");
    }

}
