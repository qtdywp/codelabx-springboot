package com.example.demo.repository.secondary;

import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecondaryRepository extends MongoRepository<User, String>
{
}
