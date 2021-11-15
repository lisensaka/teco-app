package com.taco.services;

import com.taco.models.dtos.*;
import com.taco.repository.OrderRepository;
import com.taco.models.Order;
import com.taco.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServices {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    //Create a new Order
    public Order createNewOrder(Order orders) {
        orders.setId(0);
        return orderRepository.save(orders);
    }

    // Read All Orders from Db for specific User
    public List<OrderDto> getAllOrdersForSpecificUser(String username) {
     /*   ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findByUsername(username);
        Set<Order> orders = user.getOrders();
        responseDto.setUsername(user.getUsername());

        return responseDto;*/
        List<OrderDto> orderDtos = new ArrayList<>();

        for (Order o: orderRepository.findByUser_Username(username)) {
            orderDtos.add(OrderDto.fromOrderDto(o));
        }
        return orderDtos;

    }

    // Update Order
    public Order updateOrder(Order orders) {
        return orderRepository.save(orders);
    }

    //returns all the orders this userId has in OrderDto fromat
    public List<OrderDto> getOrderByUserId(int id) {
        List<OrderDto> orderDto = new ArrayList<>();
        for (Order o : orderRepository.getAllOrdersFromUserWithId(id)) {
            orderDto.add(OrderDto.fromOrderDto(o));
        }
        return orderDto;
    }


}
