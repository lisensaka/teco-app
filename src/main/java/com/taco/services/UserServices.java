package com.taco.services;

import com.taco.models.Role;
import com.taco.models.dtos.UserDto;
import com.taco.repository.RoleRepository;
import com.taco.repository.UserRepository;
import com.taco.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServices {

    private final UserRepository userRepository;
    private final RoleServices roleServices;

    //me ane te ksaj klase do te bejm encriptimin e passwordit qe do na vij nga useri
    private final PasswordEncoder passwordEncoder;

    //create user
    public User createNewUser(User user){
        //ktu marrim passw e userit dhe e enkriptojme perpara se te futet ne DB
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

     //get user by first-name
    public User getUserByName(String name){
        return userRepository.findByFirstName(name);
    }

    //update user
    public User updateUser(User user){
        //ktu marrim passw e userit dhe e enkriptojme perpara se te futet ne DB
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //adding a role to a user
    public void addRoleToUser(String firstName, String roleName){
        User user = userRepository.findByFirstName(firstName);
        Role role = roleServices.getRoleByName(roleName);
        user.setRole(role);
    }

    //get All users in db
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user: users) {
            userDtoList.add(UserDto.fromUserDto(user));
        }
        return userDtoList;
    }

    //get All  users by some text
    public List<UserDto> getUsersByName(String name) {
        List<User> users = userRepository.findAllByFirstName(name);
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user: users) {
            userDtoList.add(UserDto.fromUserDto(user));
        }
        return userDtoList;
    }

    //get a user by email
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
