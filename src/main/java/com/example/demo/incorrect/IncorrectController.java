package com.example.demo.incorrect;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/incorrect")
public class IncorrectController {

    @Autowired
    private IncorrectRepository incorrectRepository;


    // [
    // {
    //     "id": "675c2e6649cf3d891e36481a",
    //     "word": "Zebra",
    //     "correctCnt": 0
    // },
    // {
    //     "id": "675c46bdfb527522190ab489",
    //     "word": "cobra",
    //     "correctCnt": 0
    // }
    // ]
    // 這是json response 
    
    @GetMapping("/get")
    public ResponseEntity<List<Incorrect>> getAllIncorrect() {
        List<Incorrect> incorrectList = incorrectRepository.findAll();
        List<Incorrect> limitedList = incorrectList.stream().limit(10).collect(Collectors.toList());
        return ResponseEntity.ok(limitedList);
    }
}