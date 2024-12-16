package com.example.demo.card;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        System.out.println("POST request received for single card: " + card);
        Card savedCard = cardRepository.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCard);
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        System.out.println("GET request received");
        List<Card> cards = cardRepository.findAll();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/random")
    public ResponseEntity<List<Card>> getRandomCards() {
        System.out.println("GET request received for random cards");
        List<Card> randomCards = cardRepository.findRandomCards();
        if (randomCards.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(randomCards);
    }
}