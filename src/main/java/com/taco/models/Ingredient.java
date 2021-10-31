package com.taco.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    private double price;

    // lidhje Many - Many me Taco entity
    //kam perdorur Set sepse performon me mire se List ne lidhjet Many - Many
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "taco_ingredients",
            joinColumns = @JoinColumn(name ="ingredient_id"),
            inverseJoinColumns = @JoinColumn (name = "taco_id"))
    private Set<Taco> tacos ;

    //lidhje Many - One me User entity
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Taco> getTacos() {
        return tacos;
    }

    public void setTacos(Set<Taco> tacos) {
        this.tacos = tacos;
    }


}

