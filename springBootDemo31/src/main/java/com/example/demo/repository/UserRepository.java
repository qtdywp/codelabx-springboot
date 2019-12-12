package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long>
{
    Optional<User> findById(Long Id);

    User findByUserName(String userName);

    Page<User> findAll(Pageable var1);
}