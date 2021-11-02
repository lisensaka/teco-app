package com.taco.models.dtos;

import com.taco.models.Order;
import com.taco.models.Taco;
import com.taco.models.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

        for (Taco t:order.getTacos()) {
            orderDto.tacos.add(TacoDto.convertingFromTacoToTacoDtoObj(t));
        }
        for (TacoDto i:orderDto.tacos) {
            orderDto.totalPrice += i.getTotalTacoPrice();
        }

        return orderDto;
    }
}
