package com.example.demo.incorrect;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IncorrectRepository extends MongoRepository<Incorrect, String> {
    // No additional methods needed for basic CRUD operations
}