package com.educandoweb.ecommerce.dtos.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemInputDTO {
    private Long productId;
    private Integer quantity;
}
