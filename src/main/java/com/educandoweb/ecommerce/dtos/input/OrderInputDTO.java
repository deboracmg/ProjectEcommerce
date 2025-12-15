package com.educandoweb.ecommerce.dtos.input;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInputDTO {
    private Long clientId;
    private String moment;
    private List<OrderItemInputDTO> items;
}
