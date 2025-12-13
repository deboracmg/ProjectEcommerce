package com.educandoweb.ecommerce.dtos.output;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.educandoweb.ecommerce.entities.Order;
import com.educandoweb.ecommerce.entities.OrderItem;
import com.educandoweb.ecommerce.entities.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderOutputDTO {
    private Long id;
    private Instant moment;
    private OrderStatus orderStatus;
    private String client;
    private List<OrderItemOutputDTO> items = new ArrayList<>();
    private Instant payment;
    private Double total;

    public OrderOutputDTO(Order order) {
		this.id = order.getId();
		this.moment = order.getMoment();
		this.orderStatus = order.getOrderStatus();
		this.client = order.getClient().getName();
		this.total = order.getTotal();

		if (order.getPayment() != null){
			this.payment = order.getPayment().getMoment();
		}

		List<OrderItem> products = order.getItems();
		for (OrderItem p : products) {
			OrderItemOutputDTO orderItemOutput = new OrderItemOutputDTO(p);
			this.items.add(orderItemOutput);
		}
    }
}
