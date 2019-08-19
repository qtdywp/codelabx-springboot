package com.example.demo;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import com.example.demo.dao.UserRepository;
import com.example.demo.dao.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaMyBatisDemoApplicationTests
{
    @Autowired
    private UserRepository userRepository;

    // JPA Insert
    @Test
    public void testInsert() throws Exception
    {
        User aUser = new User();
        aUser.setUserName("a01");
        aUser.setPassWord("123456");
        aUser.setRegTime(new Date());
        aUser.setRemark("rmkA01");
        aUser.setSex(1);

        userRepository.save(aUser);
    }

    // JPA Select
    @Test
    public void testSelect() throws Exception
    {
        Optional<User> obj = userRepository.findById(1L);
        User user = obj.get();
        System.out.println("testSelect():" + user.getUserName() + "," + user.getRemark());
    }

    // JPA 自定义查询
    @Test
    public void testSelectCustom() throws Exception
    {
        User user = userRepository.findByUserName("a01");
        System.out.println("testSelect():" + user.getUserName() + "," + user.getRemark());
    }

    // JPA Update
    @Test
    public void testUpdate() throws Exception
    {
        User userUpdate = userRepository.findByUserName("a01");
        userUpdate.setRemark("rmkA01_XXX");
        userRepository.save(userUpdate);
    }

    // JPA Delete
    @Test
    public void testDelete() throws Exception
    {
        userRepository.delete(userRepository.findByUserName("a01"));
    }

    // MyBatis Insert
     @Test
    public void testMyBatisInsert() throws Exception
    {
        int affectedRows = userRepository.myBatisUpdateSQL("insert into User(passWord, regTime, remark, sex, userName) values ('123123', current_timestamp, 'remark_a100', 0, 'A100')");
        System.out.println(affectedRows);
    }

    // MyBatis Select
    @Test
    public void testSelectSQL() throws Exception
    {
        List<LinkedHashMap<String, Object>> items = userRepository.myBatisSelectSQL("SELECT * FROM User");
        for (LinkedHashMap<String, Object> hashMap : items)
        {
            for (String key : hashMap.keySet())
            {
                System.out.println(key + ":" + hashMap.get(key));
            }
            System.out.println("--------------");
        }
    }

    // MyBatis Update
    @Test
    public void testMyBatisUpdate() throws Exception
    {
        int affectedRows = userRepository.myBatisUpdateSQL("update User SET remark = 'remark_a100XXX' WHERE userName = 'A100'");
        System.out.println(affectedRows);
    }

    // MyBatis Delete
    @Test
    public void testMyBatisDelete() throws Exception
    {
        userRepository.myBatisUpdateSQL("delete from User WHERE userName = 'A100'");
    }
}
