package com.taco.models.dtos;

import com.taco.models.*;

public class UserDto extends User {

    /** Method for Converting from User Object to UserDto */
    public static UserDto convertingFromUserTpUserDtoObj(User user){
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.firstName = user.getFirstName();
        userDto.lastName = user.getLastName();
        userDto.age = user.getAge();
        userDto.gender = user.getGender();
        userDto.email = user.getEmail();
        userDto.password = user.getPassword();
        userDto.role = user.getRole();
        userDto.orders = user.getOrders();

        return userDto;
    }

    /** Method for Converting the Object CardDto sent from the Api  to Card Object to save it into Db*/
    public static User convertingFromUserDtoToUserObj(UserDto userDto){
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        user.setAuthorities(userDto.getAuthorities());
        user.setEnabled(userDto.getEnabled());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setOrders(userDto.getOrders());
        return user;
    }
}
