package com.educandoweb.ecommerce.dtos.output;

import com.educandoweb.ecommerce.entities.OrderItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemOutputDTO {
    private String product;
    private Double price;
    private Integer quantity;
    private Double subtotal;

    public OrderItemOutputDTO(OrderItem item) {
        this.product = item.getProduct().getName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.subtotal = item.getSubtotal();
    }
}
