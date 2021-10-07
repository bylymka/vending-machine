package com.imperva.vendingmachine.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperva.vendingmachine.dto.ProductTypeDto;
import com.imperva.vendingmachine.mapper.ProductTypeMapper;
import com.imperva.vendingmachine.model.ProductType;
import com.imperva.vendingmachine.repository.ProductTypeRepository;
import com.imperva.vendingmachine.service.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	
	private final ProductTypeRepository productTypeRepository;
	private final ProductTypeMapper productTypeMapper;
	
	@Autowired
	public ProductTypeServiceImpl(final ProductTypeRepository productTypeRepository,
								  ProductTypeMapper productTypeMapper) {
		this.productTypeRepository = productTypeRepository;
		this.productTypeMapper = productTypeMapper;
	}
	
	@Override
	public ProductType addProductType(final ProductTypeDto productTypeDto) {
		ProductType product = productTypeRepository.findByName(productTypeDto.getName());
		
		if(product != null){
			throw new RuntimeException("The product with name: " + productTypeDto.getName() + " already exists!");
		} else {
			productTypeRepository.save(product);
		}
		
		return product;
	}
	
	@Override
	public ProductType changePrice(final Long productTypeId, final BigDecimal newPrice) {
		Optional<ProductType> product = productTypeRepository.findById(productTypeId);
		
		if(product.isPresent()){
			product.get().setPrice(newPrice);
			productTypeRepository.save(product.get());
		} else {
			throw new RuntimeException("The product with id " + productTypeId + " does not exist.");
		}
		
		return product.get();
	}
}
