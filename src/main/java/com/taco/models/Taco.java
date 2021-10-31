package com.taco.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tacos")

public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalPrice;

    @Column(name = "taco_name")
    private String tacoName;

    // lidhje Many - Many me Ingredients
    //kam perdorur Set sepse performon me mire se List ne lidhjet Many - Many
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "taco_ingredients",joinColumns = @JoinColumn(name = "taco_id"),
                            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients;

    // lidhje  Many - One me Card
    @ManyToOne(fetch = FetchType.LAZY)
    private Card card;

    //lidhje Many - One me User
/*    @ManyToOne(fetch = FetchType.LAZY)
    private User user;*/

    //lidhje Many - Many me Order
    @ManyToMany(// mendoj se duhet cascade.Type.ALL sepse nje order pa Taco nuk egziston
             )
    @JoinTable(name = "tacos_orders",joinColumns =
    @JoinColumn(name = "taco_id"),
    inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTacoName() {
        return tacoName;
    }

    public void setTacoName(String tacoName) {
        this.tacoName = tacoName;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
