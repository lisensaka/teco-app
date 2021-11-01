package com.taco.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taco.models.Order;
import com.taco.models.Taco;
import com.taco.models.User;
import com.taco.models.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private LocalDate localDate;
    private double totalPrice;
    private OrderStatus status;
    private String username;
    private Set<TacoDto> tacos = new HashSet<>();


    public static OrderDto fromOrderDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.localDate = order.getLocalDate();
        orderDto.status = order.getStatus();
        orderDto.username = order.getUser().getUsername();
        for(Taco taco : order.getTacos()){
            orderDto.tacos.add(TacoDto.fromTacoDto(taco));
        }
        for (TacoDto t : orderDto.tacos) {
            orderDto.totalPrice += t.getTotalTacoPrice();
        }
        return orderDto;
    }
}
