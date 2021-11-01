package com.taco.models.dtos;

import com.taco.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TacoDto {

    private double totalTacoPrice;
    private String tacoName ;
    private Set<IngredientDto> ingredientsDto = new HashSet<>();


    public static TacoDto fromTacoDto(Taco taco){
        TacoDto tacoDto = new TacoDto();

        for (Ingredient i: taco.getIngredients()) {
            tacoDto.ingredientsDto.add(IngredientDto.fromIngredientDto(i));

        }
        for (IngredientDto i: tacoDto.ingredientsDto) {
            tacoDto.totalTacoPrice += i.getIngredientPrice();
            tacoDto.tacoName = i.getType().name();
        }

        return tacoDto;
    }

}
