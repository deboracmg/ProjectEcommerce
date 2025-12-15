package com.educandoweb.ecommerce.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.ecommerce.dtos.input.PaymentInputDTO;
import com.educandoweb.ecommerce.entities.Order;
import com.educandoweb.ecommerce.entities.Payment;
import com.educandoweb.ecommerce.repositories.OrderRepository;
import com.educandoweb.ecommerce.repositories.PaymentRepository;
import com.educandoweb.ecommerce.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;
	
	public List<Payment> findAll(){
		return paymentRepository.findAll();
	}
	
	public Payment findById(Long id) {
		return paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	@Transactional
	public Payment insert(PaymentInputDTO dto) {
		Order order = orderService.findById(dto.getOrderId());
		Instant moment = Instant.parse(dto.getMoment());

		Payment payment = new Payment();
		payment.setOrder(order);
		payment.setMoment(moment);
		paymentRepository.save(payment);

		order.confirmPayment(payment);
		orderRepository.save(order);

		return payment;
	}
	
	@Transactional
	public void delete(Long id) {
		Payment payment = findById(id);

		Order order = payment.getOrder();
		order.removePayment();
		orderRepository.save(order);

		paymentRepository.delete(payment);
	}
}
