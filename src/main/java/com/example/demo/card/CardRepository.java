package com.example.demo.card;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, String> {
    // No additional methods needed for basic CRUD operations
}
