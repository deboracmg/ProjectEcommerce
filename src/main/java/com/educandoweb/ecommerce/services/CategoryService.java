package com.educandoweb.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.ecommerce.entities.Category;
import com.educandoweb.ecommerce.repositories.CategoryRepository;
import com.educandoweb.ecommerce.services.exceptions.DatabaseException;
import com.educandoweb.ecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Category insert(Category obj) {
		return repository.save(obj);
	}
		
	public void delete(Long id) {
		Category category = findById(id);
		try {
			repository.delete(category);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Category update(Long id, Category obj) {
		Category entity = findById(id);
		entity.setName(obj.getName());
		return repository.save(entity);
	}
}
