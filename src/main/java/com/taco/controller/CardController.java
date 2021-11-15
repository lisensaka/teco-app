package com.taco.controller;

import com.taco.models.Card;
import com.taco.models.dtos.CardDto;
import com.taco.services.CardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardServices cardServices;

    @PutMapping
    public CardDto updateCard(@RequestBody CardDto card){
        return cardServices.updateCard(card);
    }
}
