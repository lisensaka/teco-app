package com.taco.services;


import com.taco.models.dtos.TacoDto;
import com.taco.repository.IngredientRepository;
import com.taco.repository.TacoRepository;
import com.taco.models.Taco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TacoServices {

    private final TacoRepository tacoRepository;
    private final OrdersServices ordersServices;
    private final IngredientRepository ingredientRepository;

    //create Taco
    public Taco createTaco(Taco taco){
        return tacoRepository.save(taco);
    }

    // get all available tacos
    public List<TacoDto> getAllTacos(){
        List<TacoDto> tacoDtos = new ArrayList<>();
        for (Taco t: tacoRepository.findAll()) {
            tacoDtos.add(TacoDto.convertingFromTacoToTacoDtoObj(t));
        }
        return tacoDtos;
    }

    //update Taco from Db
    public Taco updateTaco(Taco taco){
         return tacoRepository.save(taco);
    }

    //delete Taco by Id
    public void deleteTacoById(Long id){
        tacoRepository.deleteById(id);
    }

    public TacoDto getTacoByIngredient(String text) {
        TacoDto tacoDtos = TacoDto.convertingFromTacoToTacoDtoObj(tacoRepository.findAllByTacoName_OrIngredientsContains(text, text));
        return tacoDtos;
    }
}
