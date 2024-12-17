package com.example.demo.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @GetMapping("/get")
    public ResponseEntity<List<Pet>> getPet() {
        try {
            List<Pet> pets = petRepository.findAll();
            return ResponseEntity.ok(pets);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/addExp")
    public ResponseEntity<String> addExpToPet(@RequestParam int exp) {
        try {
            List<Pet> pets = petRepository.findAll();
            if (pets.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pet found");
            }
            Pet pet = pets.get(0); // Assuming there is only one pet
            int newExp = Integer.parseInt(pet.getExp()) + exp;
            while (newExp >= 100) {
                pet.setLevel(String.valueOf(Integer.parseInt(pet.getLevel()) + 1));
                newExp -= 100;
            }
            pet.setExp(String.valueOf(newExp));
            petRepository.save(pet);
            return ResponseEntity.ok("Experience added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while adding experience");
        }
    }
}