package com.educandoweb.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.ecommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
