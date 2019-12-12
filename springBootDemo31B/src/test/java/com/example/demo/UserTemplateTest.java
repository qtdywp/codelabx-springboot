package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTemplateTest
{
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() throws Exception
    {
        User user = new User();
        user.setId(2l);
        user.setUserName("小明");
        user.setPassWord("12345678");
        userRepository.saveUser(user);
    }

    @Test
    public void findUserByUserName()
    {
        User user = userRepository.findUserByUserName("小明");
        System.out.println("user is " + user);
    }

    @Test
    public void updateUser()
    {
        User user = new User();
        user.setId(2l);
        user.setUserName("天空");
        user.setPassWord("888888");
        userRepository.updateUser(user);
    }

    @Test
    public void deleteUserById()
    {
        userRepository.deleteUserById(2L);
    }
}