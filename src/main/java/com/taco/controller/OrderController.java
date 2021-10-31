package com.taco.controller;

import com.taco.models.Order;
import com.taco.models.User;
import com.taco.models.dtos.OrderDto;
import com.taco.models.dtos.ResponseDto;
import com.taco.security.UserDetailsImpl;
import com.taco.security.jwtFilter.JwtAuthenticationFilter;
import com.taco.security.jwtFilter.JwtAuthorizationFilter;
import com.taco.services.OrdersServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrdersServices ordersServices;
    private UserDetailsImpl userDetails;

    //get all available orders
    @GetMapping("/by-username")
    public List<OrderDto> getAllOrders(Principal principal){
        return ordersServices.getAllOrdersForSpecificUser(principal.getName());
    }

    //put new order
    @PostMapping
    public ResponseEntity<Order> addNewOrder(@RequestBody Order orders){
        //uri eshte nje String ose adres e nje objecti ne web
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("order/").toUriString());
        orders.setId(0);
        return ResponseEntity.created(uri).body(ordersServices.createNewOrder(orders));
    }

    //update order
    @PutMapping
    public Order updateOrder(@RequestBody Order orders){
        return ordersServices.updateOrder(orders);
    }
}
