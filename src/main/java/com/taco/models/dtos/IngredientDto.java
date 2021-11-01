package com.taco.models.dtos;

import com.taco.models.Ingredient;
import com.taco.models.Taco;
import com.taco.models.Type;
import com.taco.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto {

    private Type type;

    private double ingredientPrice;

    public static IngredientDto fromIngredientDto(Ingredient ingredient){
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.type = ingredient.getType();
        ingredientDto.ingredientPrice = ingredient.getPrice();
        return ingredientDto;
    }
}
