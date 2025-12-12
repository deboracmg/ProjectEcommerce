package com.educandoweb.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.ecommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
