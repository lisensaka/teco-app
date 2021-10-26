package com.taco.controller;

import com.taco.models.User;
import com.taco.models.dtos.UserDto;
import com.taco.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserServices userServices;

    //get all users
    @GetMapping
    public List<UserDto> getAllUsers(){
        return userServices.getAllUsers();
    }

    //get user by name
    @GetMapping("/{name}")
    public List<UserDto> getAllUsers(@PathVariable String name){
        return userServices.getUsersByName(name);
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
