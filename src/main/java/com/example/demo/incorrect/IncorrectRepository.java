package com.example.demo.incorrect;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IncorrectRepository extends MongoRepository<Incorrect, String> {
    Incorrect findByWord(String word);
}