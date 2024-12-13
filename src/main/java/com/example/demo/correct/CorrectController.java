package com.example.demo.correct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.incorrect.Incorrect;
import com.example.demo.incorrect.IncorrectRepository;

@RestController
@RequestMapping("/api/correct")
public class CorrectController {

    @Autowired
    private CorrectRepository correctRepository;

    @Autowired
    private IncorrectRepository incorrectRepository;

    @PostMapping("/check")
    public ResponseEntity<Boolean> checkWord(@RequestParam String word) {
        Correct correct = correctRepository.findByWord(word);
        Incorrect incorrect = incorrectRepository.findByWord(word);
        if ((correct != null && correct.getWord().equals(word))
                || (incorrect != null && incorrect.getWord().equals(word))) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addWord(@RequestBody Correct correct) {
        Correct existingCorrect = correctRepository.findByWord(correct.getWord());
        if (existingCorrect != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Word already exists in the database");
        }
        Correct savedCorrect = correctRepository.save(correct);
        return ResponseEntity.status(HttpStatus.CREATED).body("Word added successfully");
    }
}