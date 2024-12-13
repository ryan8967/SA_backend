package com.example.demo.correct;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CorrectRepository extends MongoRepository<Correct, String> {
    Correct findByWord(String word);
}