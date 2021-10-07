package com.imperva.vendingmachine.mapper;

import org.springframework.stereotype.Service;

import com.imperva.vendingmachine.dto.ProductTypeDto;
import com.imperva.vendingmachine.model.ProductType;

@Service
public class ProductTypeMapper {
	public ProductType toDomainObject(ProductTypeDto dto) {
		return ProductType.builder()
				.name(dto.getName())
				.price(dto.getPrice())
				.build();
	}
	
	public ProductTypeDto toDto(ProductType productType) {
		return ProductTypeDto.builder()
				.name(productType.getName())
				.price(productType.getPrice())
				.build();
	}
}
