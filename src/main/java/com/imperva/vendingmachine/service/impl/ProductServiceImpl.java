package com.imperva.vendingmachine.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperva.vendingmachine.model.Product;
import com.imperva.vendingmachine.repository.ProductRepository;
import com.imperva.vendingmachine.repository.VendingMachineRepository;
import com.imperva.vendingmachine.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	private final VendingMachineRepository vendingMachineRepository;
	
	@Autowired
	public ProductServiceImpl(final ProductRepository productRepository,
							  VendingMachineRepository vendingMachineRepository) {
		this.productRepository = productRepository;
		this.vendingMachineRepository = vendingMachineRepository;
	}
	
	@Override
	public Product create(final Product product) {
		return productRepository.save(product);
	}
	
	@Override
	public Product findById(final Long id) {
		return productRepository.findById(id).
				orElseThrow(() -> new IllegalArgumentException("The product with id " + id + " does not exist."));
	}
	
	
	@Override
	public void delete(final Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		product.ifPresent(productRepository::delete);
	}
}
