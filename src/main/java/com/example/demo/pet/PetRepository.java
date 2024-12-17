package com.example.demo.pet;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetRepository extends MongoRepository<Pet, String> {
    Optional<Pet> findByUserId(String userId);
}
