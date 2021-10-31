package com.taco.services;

import com.taco.models.dtos.IngredientDto;
import com.taco.repository.IngredientRepository;
import com.taco.models.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServices {

    private final IngredientRepository ingredientRepository;

    //create ingredient
    public Ingredient createIngredient(Ingredient ingredients){
        return ingredientRepository.save(ingredients);
    }

    //update ingredient
    public Ingredient updateIngredients(Ingredient ingredients){
        return ingredientRepository.save(ingredients);
    }

    //read all availavle ingredients
    public List<IngredientDto> getAllAvailableIngredients(){
        List<IngredientDto> ingredientDtos = new ArrayList<>();
        for (Ingredient i:ingredientRepository.findAll()) {
            ingredientDtos.add(IngredientDto.fromIngredientDto(i));
        }
        return ingredientDtos;
    }

    //read ingredients by name
    public List<IngredientDto> readIngredientsByName(String text){
        List<IngredientDto> ingredientDtos = new ArrayList<>();
        for (Ingredient i:ingredientRepository.findByIngredientsName(text)) {
            ingredientDtos.add(IngredientDto.fromIngredientDto(i));
        }
        return ingredientDtos;
    }

    //delete ingredient
    public void deleteIngredientById(long id){
        ingredientRepository.deleteById(id);

    }
}
