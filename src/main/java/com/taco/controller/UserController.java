package com.taco.controller;

import com.taco.models.User;
import com.taco.models.dtos.OrderDto;
import com.taco.models.dtos.UserDto;
import com.taco.services.OrdersServices;
import com.taco.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserServices userServices;
    private final OrdersServices ordersServices;

 /*   //get all users
    @GetMapping("/test")
    public List<OrderDto> getAllUsersByName(Principal principal){
        UserDto userDto = userServices.getUserByUsername(principal.getName());
        //return userServices.getAllUsers();
        return ordersServices.getOrderByUserId(Math.toIntExact(userDto.getId()));
    }*/

    //get user by name
    @GetMapping("/name")
    public List<UserDto> getAllUsers(Principal principal){
        return userServices.getUsersByUsername(principal.getName());
    }

    //put new user
    @PostMapping
    public ResponseEntity<User> addNewUser(@Valid @RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
        user.setId(0L);
        return ResponseEntity.created(uri).body(userServices.createNewUser(user));
    }

    //update user
    @PutMapping
    public User updateUser(@Valid @RequestBody User user){
        return userServices.updateUser(user);
    }

}
