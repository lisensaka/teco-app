package com.taco.models.dtos;

import com.taco.models.*;

import java.io.Serializable;

public class UserDto implements Serializable {

    private String firstName;
    private String lastName;
    private  int age;
    private String gender;
    private String email;
    private String username;
    private RoleDto roleDto;
    private String password;
    private String authorities;

    /** Method for Converting from User Object to UserDto */
    public static UserDto convertingFromUserTpUserDtoObj(User user){
        UserDto userDto = new UserDto();
        //userDto.id = user.getId();
        userDto.firstName = user.getFirstName();
        userDto.username = user.getUsername();
        userDto.lastName = user.getLastName();
        userDto.age = user.getAge();
        userDto.gender = user.getGender();
        userDto.email = user.getEmail();
        userDto.password = user.getPassword();
        userDto.roleDto = RoleDto.fromRoleDto(user.getRole());
        userDto.authorities = user.getAuthorities();
        //userDto.setRoleDto(RoleDto.fromRoleDto(user.getRole()));
        //userDto.orders = user.getOrders();

        return userDto;
    }

    /** Method for Converting the Object CardDto sent from the Api  to Card Object to save it into Db*/
    public static User convertingFromUserDtoToUserObj(UserDto userDto){
        User user = new User(userDto.getUsername(), userDto.getPassword(), userDto.getRoleDto(), userDto.getAuthorities(), userDto.getEmail(), userDto.getLastName(),userDto.getFirstName());
       /* user.firstName = user.getFirstName();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        //user.setAuthorities(userDto.getAuthorities());
        //user.setEnabled(userDto.getEnabled());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
       // user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
       // user.setOrders(userDto.getOrders());*/
        return user;
   }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String getAuthorities) {
        this.authorities = getAuthorities;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
