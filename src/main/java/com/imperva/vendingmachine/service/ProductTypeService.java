package com.imperva.vendingmachine.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.imperva.vendingmachine.dto.ProductTypeDto;
import com.imperva.vendingmachine.model.ProductType;

public interface ProductTypeService {
	ProductType addProductType(ProductTypeDto productTypeDto);
	ProductType changePrice(Long productTypeId, BigDecimal newPrice);
}
