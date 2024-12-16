package com.example.demo.card;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Aggregation;
import java.util.List;

public interface CardRepository extends MongoRepository<Card, String> {
    @Aggregation(pipeline = {
        "{ $sample: { size: 10 } }"
    })
    List<Card> findRandomCards();
}