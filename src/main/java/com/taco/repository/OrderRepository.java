package com.taco.repository;

import com.taco.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {


    //Query per te marr gjith porosit qe ka ber nje user
//    @Query("select o " +
//          //  "t.tacoName, u.username, t.totalPrice, i.price " +
//            "from User u " +
//            "join u.orders o " +
//           // "join o.tacos t "+
//           // "join t.ingredients i "+
//            "where u.username like :username ")
    List<Order> findByUser_Username(@Param("username") String username);



    @Query("select o from User u join u.orders o where u.id = :id ")
    List<Order> getAllOrdersFromUserWithId(@Param("id") int id);
}
