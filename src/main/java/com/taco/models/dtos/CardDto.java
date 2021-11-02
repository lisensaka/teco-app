package com.taco.models.dtos;

import com.taco.models.Card;
import com.taco.models.Taco;

import java.util.ArrayList;
import java.util.List;


public class CardDto extends Card{

        private UserDto userDto;
        private List<TacoDto> tacoDto = new ArrayList<>();

        /** Method for Converting from Card Object to CardDto */
        public static CardDto convertingFromCardToCardDtoObj(Card card){
                CardDto cardDto = new CardDto();
                cardDto.userDto = UserDto.convertingFromUserTpUserDtoObj(card.getUser());
                for (Taco t:card.getTaco()) {
                        cardDto.tacoDto.add(TacoDto.convertingFromTacoToTacoDtoObj(t));
                }
                return cardDto;
        }

        /** Method for Converting the Object CardDto sent from the Api  to Card Object to save it into Db*/
        public static Card convertingFromCardDtoToCardObj(CardDto cardDto){
                Card card = new Card();
                List<Taco> tempVariableDtos = new ArrayList<>();
                card.setUser(cardDto.getUser());
                for (Taco t: cardDto.getTaco()) {
                        tempVariableDtos.add(t);
                }
                card.setTaco(tempVariableDtos);
                return card;
        }
}
