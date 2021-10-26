package com.taco.repository;

import com.taco.models.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    /*Orders createNewIngredient(Orders orders);
    List<Orders> readAllOrders();
    Orders updateOrder(Orders orders);*/

    @Query("select o " +
            "from User u " +
            "join u.orders o " +
            "where u.username = :username ")
    List<Order> findAllOrdersByUsername(@Param("username") String username);
}
