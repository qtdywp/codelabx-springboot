package com.example.demo.repository.primary;

import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrimaryRepository extends MongoRepository<User, String>
{
}
