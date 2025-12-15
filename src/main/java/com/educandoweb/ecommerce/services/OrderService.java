package com.educandoweb.ecommerce.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.ecommerce.dtos.input.OrderInputDTO;
import com.educandoweb.ecommerce.dtos.input.OrderItemInputDTO;
import com.educandoweb.ecommerce.dtos.output.OrderOutputDTO;
import com.educandoweb.ecommerce.entities.Order;
import com.educandoweb.ecommerce.entities.OrderItem;
import com.educandoweb.ecommerce.entities.Product;
import com.educandoweb.ecommerce.entities.User;
import com.educandoweb.ecommerce.repositories.OrderRepository;
import com.educandoweb.ecommerce.services.exceptions.DatabaseException;
import com.educandoweb.ecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;

	public List<OrderOutputDTO> findAll(){
		List<OrderOutputDTO> orders = new ArrayList<>();
		for ( Order o : orderRepository.findAll()) {
			orders.add(new OrderOutputDTO(o));
		}
		return orders;
	}
	
	public Order findById(Long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public OrderOutputDTO findOrderOutputById(Long id) {
		return new OrderOutputDTO(findById(id));
	}

	public OrderOutputDTO insert(OrderInputDTO dto) {
		Long clientId = dto.getClientId();
		User client = userService.findById(clientId);

		Instant moment = Instant.parse(dto.getMoment());
		Order order = new Order(null, moment, client);

		createOrderItems(order, dto.getItems());

		return new OrderOutputDTO(orderRepository.save(order));
	}

	public void createOrderItems(Order order, List<OrderItemInputDTO> items) {
		for (OrderItemInputDTO itemDTO : items) {
			Long productId = itemDTO.getProductId();
			Product product = productService.findById(productId);

			OrderItem orderItem = new OrderItem(order, product, itemDTO.getQuantity());
			order.getItems().add(orderItem);
		}
	}
		
	public void delete(Long id) {
		Order order = findById(id);
		try {
			orderRepository.delete(order);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}