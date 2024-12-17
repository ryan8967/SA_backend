package com.example.demo.word;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordRepository extends MongoRepository<Word, String> {
    @Aggregation(pipeline = {
        "{ $sample: { size: 10 } }"
    })
    List<Word> findRandomWords();
}