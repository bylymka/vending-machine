package com.imperva.vendingmachine.service;

import com.imperva.vendingmachine.model.Product;

public interface ProductService {
	Product create(Product product);
	Product findById(Long id);
	void delete(Long productId);
}
