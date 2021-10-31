package com.taco.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taco.models.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate localDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //Lidhja Many - Many me User entity
    //kam perdorur Set sepse performon me mire se List ne lidhjet Many - Many
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
   /* @JoinTable(name = "users_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))*/
    private User user;

    //Lidhja Many - Many me Taco entity
    // mappedBy = po i tregojme se kush do jet owner-i i lidhjes
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tacos_orders",joinColumns =
    @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "taco_id"))
    private Set<Taco> tacos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Taco> getTacos() {
        return tacos;
    }

    public void setTacos(Set<Taco> tacos) {
        this.tacos = tacos;
    }
}
