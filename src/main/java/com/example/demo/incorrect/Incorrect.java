package com.example.demo.incorrect;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "incorrect")
public class Incorrect {
    @Id
    private String id;
    private String word;
    private int correctCnt;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCorrectCnt() {
        return correctCnt;
    }

    public void setCorrectCnt(int correctCnt) {
        this.correctCnt = correctCnt;
    }
}