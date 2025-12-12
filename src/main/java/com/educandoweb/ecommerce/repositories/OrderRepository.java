package com.educandoweb.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.ecommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
