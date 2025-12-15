package com.educandoweb.ecommerce.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.ecommerce.dtos.input.PaymentInputDTO;
import com.educandoweb.ecommerce.entities.Payment;
import com.educandoweb.ecommerce.services.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService service;
	
	@GetMapping
	public ResponseEntity<List<Payment>> findAll() {
		List<Payment> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{orderId}")
	public ResponseEntity<Payment> findById(@PathVariable Long orderId){
		Payment obj = service.findById(orderId);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Payment> insert(@RequestBody PaymentInputDTO dto) {
		Payment payment = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
								.buildAndExpand(payment.getId()).toUri();
		return ResponseEntity.created(uri).body(payment);
	}
		
	@DeleteMapping(value = "/{orderId}")
	public ResponseEntity<Void> delete(@PathVariable Long orderId) {
		service.delete(orderId);
		return ResponseEntity.noContent().build();
	}
}
