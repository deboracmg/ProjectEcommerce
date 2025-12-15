package com.educandoweb.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.ecommerce.dtos.input.ProductInputDTO;
import com.educandoweb.ecommerce.entities.Category;
import com.educandoweb.ecommerce.entities.Product;
import com.educandoweb.ecommerce.repositories.ProductRepository;
import com.educandoweb.ecommerce.services.exceptions.DatabaseException;
import com.educandoweb.ecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;

	@Autowired
	private CategoryService categoryService;
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Product insert(ProductInputDTO dto) {
		Product product = new Product();
		setData(product, dto, false);
		return repository.save(product);
	}
	
	public Product update(Long id, ProductInputDTO dto) {
		Product entity = findById(id);
		setData(entity, dto, true);
		return repository.save(entity);
	}
	
	private void setData(Product entity, ProductInputDTO dto, boolean clearCategories) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		
		if (clearCategories){
			entity.getCategories().clear();
		}
		
		for (Long catId : dto.getCategoriesId()) {
			Category category = categoryService.findById(catId);
			entity.getCategories().add(category);
		}
	}
	
	public void delete(Long id) {
	Product product = findById(id);
	try {
		repository.delete(product);
	} catch (DataIntegrityViolationException e) {
		throw new DatabaseException(e.getMessage());
	}
	}
}
