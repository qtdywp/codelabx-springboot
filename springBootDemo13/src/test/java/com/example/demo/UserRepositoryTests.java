package com.example.demo;

import java.util.Date;

import com.example.demo.dao.UserRepository;
import com.example.demo.dao.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests
{
    @Autowired
    private UserRepository userRepository;

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

    @Test
    public void testSelect() throws Exception
    {
        User user = userRepository.findByUserName("a01");
        System.out.println("testSelect():" + user.getUserName() + "," + user.getRemark());
    }

    @Test
    public void testUpdate() throws Exception
    {
        User userUpdate = userRepository.findByUserName("a01");
        userUpdate.setRemark("rmkA01_XXX");
        userRepository.save(userUpdate);
    }

    @Test
    public void testDelete() throws Exception
    {
        userRepository.delete(userRepository.findByUserName("a01"));
    }

}
