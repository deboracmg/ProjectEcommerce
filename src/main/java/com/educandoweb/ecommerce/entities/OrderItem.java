package com.educandoweb.ecommerce.entities;

import java.io.Serializable;

import com.educandoweb.ecommerce.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_order_item")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonPropertyOrder({ "product", "price", "quantity", "subtotal" })
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@EqualsAndHashCode.Include
	@JsonIgnore
	private OrderItemPK id = new OrderItemPK();
	
	private Integer quantity;
	private Double price;

	public OrderItem(Order order, Product product, Integer quantity) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = product.getPrice();
	}

	@JsonBackReference
	public Order getOrder() {
		return id.getOrder();
	}
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	@JsonManagedReference
	public Product getProduct() {
		return id.getProduct();
	}
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	public Double getSubtotal() {
		return price * quantity;
	}
}
