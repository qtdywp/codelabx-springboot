package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@SpringBootTest
public class RepositoryTest
{
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() throws Exception
    {
        for (long i = 0; i < 30; i++)
        {
            User user = new User();
            user.setId(i);
            user.setUserName("小明" + i);
            user.setPassWord("123456");
            userRepository.save(user);
        }
    }

    @Test
    public void findUserById()
    {
        Optional<User> obj = userRepository.findById(2L);
        User user = obj.get();
        System.out.println("user is " + user);
    }

    @Test
    public void findUserByUserName()
    {
        User user = userRepository.findByUserName("小明1");
        System.out.println("user is " + user);
    }

    @Test
    public void updateUser()
    {
        User user = new User();
        user.setId(2l);
        user.setUserName("小明XXX");
        user.setPassWord("88888888");
        userRepository.save(user);
    }

    @Test
    public void deleteUserById()
    {
        userRepository.deleteById(2l);
    }

    @Test
    public void testPage()
    {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(1, 10, sort);
        Page page = userRepository.findAll(pageable);
        for(Object user : page.getContent())
        {
            System.out.println(user);
        }
    }
}