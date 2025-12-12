package com.educandoweb.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
