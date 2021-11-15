package com.taco.controller;


import com.taco.models.dtos.OrderDto;
import com.taco.models.dtos.UserDto;
import com.taco.services.OrdersServices;
import com.taco.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
// @CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserServices userServices;
    private final OrdersServices ordersServices;

    //get all Orders for Logged user, available only for logged in user
    @GetMapping()
    public ResponseEntity<List<OrderDto>> getAllUsersByName(Principal principal){
        return new ResponseEntity<>(ordersServices.getAllOrdersForSpecificUser(principal.getName()), HttpStatus.OK);
    }

    //get all available users, available only for admin role
    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        return userServices.getAllAvailableUsers();
    }

    //put new user
    @PostMapping
    public ResponseEntity<UserDto> addNewUser(@Valid @RequestBody UserDto user){
        //URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
        return new ResponseEntity<UserDto>(userServices.createNewUser(user),HttpStatus.CREATED);
    }

    //update user
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user){
        return new ResponseEntity<>(userServices.updateUser(user),HttpStatus.OK);
    }

    //delete user by id, only admin role have the right
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable long id){
        userServices.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
