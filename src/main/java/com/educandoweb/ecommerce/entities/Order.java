package com.educandoweb.ecommerce.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.educandoweb.ecommerce.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_order")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="GMT")
	private Instant moment;
	
	private Integer orderStatus;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private User client;
	
	@OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL)
	@JsonManagedReference
	@Setter(value = AccessLevel.NONE)
	private List<OrderItem> items = new ArrayList<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	public Order(Long id, Instant moment, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(OrderStatus.WAITING_PAYMENT);
		this.client = client;
	}		

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}
	public Double getTotal() {
		Double total = 0.0;
		for (OrderItem x : items) {
			total+=x.getSubtotal();
		}
		return total;
	}

	public void confirmPayment(Payment payment) {
		this.payment = payment;
		setOrderStatus(OrderStatus.PAID);
	}

	public void removePayment() {
		this.payment = null;
		setOrderStatus(OrderStatus.WAITING_PAYMENT);
	}
}
