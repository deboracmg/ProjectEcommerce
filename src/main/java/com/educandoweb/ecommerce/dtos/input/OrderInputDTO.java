package com.educandoweb.ecommerce.dtos.input;

import java.util.List;

import com.educandoweb.ecommerce.entities.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInputDTO {
    private Long clientId;
    private OrderStatus orderStatus;
    private String moment;
    private List<OrderItemInputDTO> items;
}
