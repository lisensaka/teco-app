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
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToMany
    @JoinTable(name = "users_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> user;

    //Lidhja Many - Many me Taco entity
    // mappedBy = po i tregojme se kush do jet owner-i i lidhjes
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tacos_orders",joinColumns =
    @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "taco_id"))
    private Set<Taco> tacos;

    public String toString() {
        return "Order(id=" + this.getId() + ", localDate=" + this.getLocalDate() + ", status=" + this.getStatus() + ")";
    }
}
