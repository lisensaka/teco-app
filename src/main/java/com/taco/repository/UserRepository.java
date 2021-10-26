package com.taco.repository;

import com.taco.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   // @Query("select u from User u where u.firstName like :theName")
    List<User> findAllByFirstName(String firstName);

    User findByFirstName(String firstName);

   /* @Query("select u from User u where u.email like :email")
    User getTheUserByEmail(@Param("email") String email);*/

    User findByEmail(String email);

    User findByUsername(String username);

}
