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

    /** Method for Converting from Taco Object to TacoDto */
    public static TacoDto convertingFromTacoToTacoDtoObj(Taco taco){
        TacoDto tacoDto = new TacoDto();

        for (Ingredient i: taco.getIngredients()) {
            tacoDto.ingredientsDto.add(IngredientDto.fromIngredientDto(i));
        }
        for (IngredientDto i: tacoDto.ingredientsDto) {
            tacoDto.totalTacoPrice += i.getIngredientPrice();
            //tacoDto.tacoName = i.getType().name();
            tacoDto.tacoName = taco.getTacoName();
        }
        return tacoDto;
    }

}
