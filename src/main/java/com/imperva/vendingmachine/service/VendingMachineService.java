package com.imperva.vendingmachine.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.imperva.vendingmachine.dto.ProductResponseDto;
import com.imperva.vendingmachine.dto.ProductSuppliesDto;
import com.imperva.vendingmachine.model.Product;
import com.imperva.vendingmachine.model.ProductType;
import com.imperva.vendingmachine.model.VendingMachine;

public interface VendingMachineService {
	VendingMachine getById(Long id);
	BigDecimal getBalance(Long vmId);
	VendingMachine takeMoney(Long vmId);
	ProductResponseDto buyProduct(Long vmId, Long productTypeId, BigDecimal money);
	void resupply(ProductSuppliesDto products, Long vmId);
	Map<ProductType, Long> getProductSuppliesInfo(Long vmId);
	VendingMachine addNewVm(VendingMachine vendingMachine);
}
