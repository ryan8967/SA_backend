package com.example.demo.word;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/cards")
public class WordController {

    @Autowired
    private WordRepository wordRepository;

    @PostMapping
    public ResponseEntity<Word> createCard(@RequestBody Word word) {
        System.out.println("POST request received for single card: " + word);
        Word savedWord = wordRepository.save(word);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWord);
    }

    @GetMapping
    public ResponseEntity<List<Word>> getAllCards() {
        System.out.println("GET request received");
        List<Word> words = wordRepository.findAll();
        return ResponseEntity.ok(words);
    }

    @GetMapping("/random")
    public ResponseEntity<List<Word>> getRandomCards() {
        System.out.println("GET request received for random cards");
        List<Word> randomWords = wordRepository.findRandomWords();
        if (randomWords.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(randomWords);
    }
}