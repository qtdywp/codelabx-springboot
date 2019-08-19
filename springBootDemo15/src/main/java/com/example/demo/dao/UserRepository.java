package com.example.demo.dao;

import com.example.demo.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, CustomizedRepository
{
    // 自定义查询
    User findByUserName(String userName);
}
