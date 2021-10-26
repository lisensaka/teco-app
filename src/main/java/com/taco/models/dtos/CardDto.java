package com.taco.models.dtos;

import com.taco.models.Taco;
import com.taco.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

        private Long id;
        private User user;
        private List<Taco> taco;
}
