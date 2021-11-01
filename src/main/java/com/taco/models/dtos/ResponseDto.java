package com.taco.models.dtos;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.taco.models.Taco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private Taco taco;
    private String username;
    private double totalPrice;
    private double ingredientPrice;

    public static ResponseDto fromResponseDto(List<Object> list){
        List<ResponseDto> finalList = new ArrayList<>();
        for (Object s: list) {
           // finalList.add();
        }

        ResponseDto responseDto = new ResponseDto();

        for (ResponseDto s:finalList) {
            responseDto.taco = s.getTaco();
            responseDto.username = s.getUsername();
            responseDto.totalPrice += s.getIngredientPrice();
        }

       return responseDto;
    }


}
