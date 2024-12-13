package com.example.demo.incorrect;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.correct.Correct;
import com.example.demo.correct.CorrectRepository;

@RestController
@RequestMapping("/api/incorrect")
public class IncorrectController {

    @Autowired
    private IncorrectRepository incorrectRepository;

    @Autowired
    private CorrectRepository correctRepository;

    @GetMapping("/get")
    public ResponseEntity<List<Incorrect>> getAllIncorrect() {
        List<Incorrect> incorrectList = incorrectRepository.findAll();
        List<Incorrect> limitedList = incorrectList.stream().limit(10).collect(Collectors.toList());
        return ResponseEntity.ok(limitedList);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addWord(@RequestBody Incorrect incorrect) {
        Incorrect existingIncorrect = incorrectRepository.findByWord(incorrect.getWord());
        if (existingIncorrect != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Word already exists in the database");
        }
        Incorrect savedIncorrect = incorrectRepository.save(incorrect);
        return ResponseEntity.status(HttpStatus.CREATED).body("Word added successfully");
    }

    @PostMapping("/reviewed")
    public ResponseEntity<String> incrementCorrectCnt(@RequestParam String word) {
        Incorrect incorrect = incorrectRepository.findByWord(word);
        if (incorrect == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Word not found in the database");
        }
        incorrect.setCorrectCnt(incorrect.getCorrectCnt() + 1);
        if (incorrect.getCorrectCnt() >= 3) {
            incorrectRepository.delete(incorrect);
            Correct correct = new Correct();
            correct.setWord(incorrect.getWord());
            correctRepository.save(correct);
            return ResponseEntity.ok("Word moved to correct database");
        } else {
            incorrectRepository.save(incorrect);
            return ResponseEntity.ok("Correct count incremented successfully");
        }
    }
}