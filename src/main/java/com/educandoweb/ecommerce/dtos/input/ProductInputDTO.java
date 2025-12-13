package com.educandoweb.ecommerce.dtos.input;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInputDTO {
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
    private List<Long> categoriesId;
}
