package com.example.demo.correct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/correct")
public class CorrectController {

    @Autowired
    private CorrectRepository correctRepository;

    @PostMapping("/check")
    public ResponseEntity<Boolean> checkWord(@RequestParam String word) {
        Correct correct = correctRepository.findByWord(word);
        if (correct != null && correct.getWord().equals(word)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }
}