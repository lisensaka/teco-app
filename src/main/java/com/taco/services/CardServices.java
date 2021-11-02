package com.taco.services;

import com.taco.models.dtos.CardDto;
import com.taco.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServices {

    private final CardRepository cardRepository;

    //update card
    public CardDto updateCard(CardDto cardDto){
         cardRepository.save(CardDto.convertingFromCardDtoToCardObj(cardDto));
         return cardDto;
    }
}
