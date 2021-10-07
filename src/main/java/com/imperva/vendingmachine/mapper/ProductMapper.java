package com.imperva.vendingmachine.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperva.vendingmachine.dto.ProductDto;
import com.imperva.vendingmachine.model.Product;
import com.imperva.vendingmachine.repository.ProductTypeRepository;
import com.imperva.vendingmachine.repository.VendingMachineRepository;

@Service
public class ProductMapper {
	
	private final VendingMachineRepository vendingMachineRepository;
	private final ProductTypeRepository productTypeRepository;
	
	@Autowired
	public ProductMapper(final VendingMachineRepository vendingMachineRepository,
						 ProductTypeRepository productTypeRepository) {
		this.vendingMachineRepository = vendingMachineRepository;
		this.productTypeRepository = productTypeRepository;
	}
	
	public Product toDomainObject (ProductDto dto) {
		return Product.builder()
				.vendingMachine(vendingMachineRepository.getById(dto.getVendingMachineId()))
				.type(productTypeRepository.findByName(dto.getName()))
				.build();
	}
	
	public ProductDto toDto(Product product) {
		return ProductDto.builder()
				.name(product.getType().getName())
				.price(product.getType().getPrice())
				.vendingMachineId(product.getVendingMachine().getId())
				.build();
	}
}
