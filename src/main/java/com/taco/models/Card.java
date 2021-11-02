package com.taco.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    protected Long id;

    //lidhje One - One me User

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    protected User user;


    // lidhje One - Many me tacon

    @OneToMany(mappedBy = "card",
            cascade = {CascadeType.REFRESH,CascadeType.DETACH
    ,CascadeType.MERGE})
    protected List<Taco> taco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Taco> getTaco() {
        return taco;
    }

    public void setTaco(List<Taco> taco) {
        this.taco = taco;
    }
}
