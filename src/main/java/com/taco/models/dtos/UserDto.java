package com.taco.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taco.models.*;
import com.taco.models.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String email;
    private String password;

    private String roleDto;
    private CardDto cardDto;

    private List<Ingredient> ingredient;
    private List<Taco> tacos;
    private Set<Order> orders = new HashSet<>();

    public static UserDto fromUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.firstName = user.getFirstName();
        userDto.lastName = user.getLastName();
        userDto.age = user.getAge();
        userDto.gender = user.getGender();
        userDto.email = user.getEmail();
        userDto.password = user.getPassword();

        userDto.roleDto = user.getRole().getRoleType().toString();
//        userDto.cardDto = new CardDto(user.getCard().getId(),user.getCard().getUser(),user.getCard().getTaco());
        return userDto;
    }
}
