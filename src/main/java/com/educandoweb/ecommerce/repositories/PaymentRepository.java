package com.educandoweb.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.ecommerce.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
	
}
