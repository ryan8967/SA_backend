package com.example.demo.card;

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
@CrossOrigin(origins = "localhost:8081") // Allow specific origin
@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    // POST: Create a new card
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        System.out.println("POST request received: " + card);
        Card savedCard = cardRepository.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCard);
    }

    // GET: Retrieve all cards
    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        System.out.println("GET request received");
        List<Card> cards = cardRepository.findAll();
        return ResponseEntity.ok(cards);
    }
}
