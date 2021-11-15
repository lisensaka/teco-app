package com.taco.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taco.models.dtos.RoleDto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    protected Long id;

    @Column(name = "first_name",nullable = false,length = 50)
    @Size(min = 3,message = "The first name should contain at least 3 letters")
    protected String firstName;

    @Column(name = "last_name",nullable = false,length = 50)
    @Size(min = 3,message = "The last name should contain at least 3 letters")
    protected String lastName;

    protected int age;

    protected String gender;

    @Email()
    @Column(nullable = false,unique = true, length = 100)
    protected String email;

    @Column(nullable = false)
    protected String username;

    @Column(nullable = false)
    protected String password;

    protected String authorities = "";

    protected int enabled ;

    public User(String username, String password, Role role, String authorities,String email,String lastName, String firstName) {
        this.firstName = firstName;
        this.lastName=lastName;
        this.email=email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
        this.enabled = 1;
    }

    public User() {
    }
//lidhja Many - One me Role Entitet

    //nuk mendoj se na duhet lidhje e dy-anshme ne entitetet User-Role pasi nga roli
    //nuk do kerkojme info dhe kjo lidhje e nje-anshme performon
    //shum mire per CRUD

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    protected Role role;

    // One - Many me Ingredient Entitet

   /* @OneToMany(mappedBy = "user")
    private List<Ingredient> ingredient;*/

/*    // Lidhja One - Many me Tako Entitet
    @OneToMany(mappedBy = "user")
    private List<Taco> tacos;*/

    //Lidhja One - One me Card Entitet

    // Lidhja Many - Many me Oders entity
//kam perdorur Set sepse performon me mire se List ne lidhjet Many - Many

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
   /* @JoinTable(name = "users_orders",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))*/
    protected Set<Order> orders = new HashSet<>();

    public User(String username, String password, RoleDto roleDto, String authorities, String email, String lastName, String firstName) {

    }


    // me kte metod kemi mundesin qe permissions ti ruajme me shum se nje here per user, pra te kalojm nga nje permission, ne shum permissions per nje
    //user te caktuar
    public List<String> getAuthoritisList(){
        if(this.authorities.length() > 0){
            return Arrays.asList(this.authorities.split(","));
        }
        return new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
