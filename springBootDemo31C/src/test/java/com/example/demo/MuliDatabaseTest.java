package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.primary.PrimaryRepository;
import com.example.demo.repository.secondary.SecondaryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MuliDatabaseTest
{
    private PrimaryRepository primaryRepository;
    @Autowired
    public void setPrimaryRepository(PrimaryRepository primaryRepository)
    {
        this.primaryRepository = primaryRepository;
    }

    private SecondaryRepository secondaryRepository;
    @Autowired
    public void setSecondaryRepository(SecondaryRepository secondaryRepository)
    {
        this.secondaryRepository = secondaryRepository;
    }

    @Test
    public void TestSave()
    {
        System.out.println("测试开始");

        // 保存数据
        this.primaryRepository.save(new User(1L, "小张", "123456"));
        this.secondaryRepository.save(new User(2L, "小王", "111111"));

        // 查询数据
        List<User> primaries = this.primaryRepository.findAll();
        for (User primary : primaries)
        {
            System.out.println(primary.toString());
        }

        // 查询数据
        List<User> secondaries = this.secondaryRepository.findAll();
        for (User secondary : secondaries)
        {
            System.out.println(secondary.toString());
        }
        System.out.println("测试完成");
    }
}
