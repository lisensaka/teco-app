package com.taco.controller;

import com.taco.models.Ingredient;
import com.taco.models.Type;
import com.taco.models.dtos.IngredientDto;
import com.taco.services.IngredientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientServices ingredientServices;

    //get all available ingredients
    @GetMapping
    public List<IngredientDto> getAllIngredients(){
        return ingredientServices.getAllAvailableIngredients();
    }

    //get ingredients by name
    @GetMapping("/{text}")
    public List<IngredientDto> getAllIngredients(@PathVariable String text){
        return ingredientServices.readIngredientsByName(text);
    }

    //put new ingredient
    @PostMapping
    public ResponseEntity<Ingredient> addNewIngredient(@RequestBody Ingredient ingredients){
        //uri eshte nje String ose adres e nje objecti ne web
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("ingredients/").toUriString());
        ingredients.setId(0L);
        return ResponseEntity.created(uri).body(ingredientServices.createIngredient(ingredients));
    }

    //update ingredient
    @PutMapping
    public Ingredient updateIngredient(@RequestBody Ingredient ingredients){
        return ingredientServices.updateIngredients(ingredients);
    }

    //delete ingredient
    @DeleteMapping("/{id}")
    public String deleteIngredientById(@PathVariable int id){
        ingredientServices.deleteIngredientById(id);
        return "The deleted ingredient id is - " + id;
    }
}
