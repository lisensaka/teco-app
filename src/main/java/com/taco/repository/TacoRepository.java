package com.taco.repository;

import com.taco.models.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TacoRepository extends JpaRepository<Taco,Long>{

    @Query("select t.tacoName, t.totalPrice, i.price from Taco t " +
            " join t.orders o " +
            " join t.ingredients i " +
            " where o.id = :id")
    Taco findTacoByOrderId(@Param("id") int id);

    Taco findAllByTacoName_OrIngredientsContains(String text, String text2);

}
