package com.imperva.vendingmachine.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperva.vendingmachine.dto.ProductResponseDto;
import com.imperva.vendingmachine.dto.ProductSuppliesDto;
import com.imperva.vendingmachine.model.Product;
import com.imperva.vendingmachine.model.ProductType;
import com.imperva.vendingmachine.model.VendingMachine;
import com.imperva.vendingmachine.repository.ProductRepository;
import com.imperva.vendingmachine.repository.VendingMachineRepository;
import com.imperva.vendingmachine.service.ProductService;
import com.imperva.vendingmachine.service.VendingMachineService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VendingMachineServiceImpl implements VendingMachineService {
	
	private final VendingMachineRepository vendingMachineRepository;
	private final ProductRepository productRepository;
	private final ProductService productService;
	
	@Autowired
	public VendingMachineServiceImpl(final VendingMachineRepository vendingMachineRepository,
									 ProductRepository productRepository, ProductService productService) {
		this.vendingMachineRepository = vendingMachineRepository;
		this.productRepository = productRepository;
		this.productService = productService;
	}
	
	@Override
	public VendingMachine getById(final Long id) {
		return vendingMachineRepository.getById(id);
	}
	
	@Override
	public BigDecimal getBalance(final Long vmId) {
		return vendingMachineRepository.getById(vmId).getBalance();
	}
	
	@Override
	public VendingMachine takeMoney(final Long vmId) {
		VendingMachine vm = vendingMachineRepository.getById(vmId);
		vm.setBalance(BigDecimal.ZERO);
		return vendingMachineRepository.save(vm);
	}
	
	@Override
	public ProductResponseDto buyProduct(final Long vmId, final Long productTypeId, final BigDecimal money) {
		ProductResponseDto productWithChange = new ProductResponseDto();
		LinkedList<Product> availableProducts = productRepository.findByTypeIdAndVendingMachineId(productTypeId, vmId);
		
		if(!availableProducts.isEmpty()) {
			Product product = availableProducts.peek();
			if(money.compareTo(product.getType().getPrice()) < 0) {
				throw new RuntimeException("Sorry:( Not enough money to buy this product!");
			} else {
				productService.delete(product.getId());
				final BigDecimal change = money.subtract(product.getType().getPrice());
				productWithChange.setProduct(product);
				productWithChange.setChange(change);
			}
		}
		return productWithChange;
	}
	
	@Override
	public void resupply(final ProductSuppliesDto products, Long vmId) {
		if(products.getProduct()!= null && products.getQuantity() != null) {
			int q = products.getQuantity();
			while (q > 0) {
				productService.create(products.getProduct());
				q--;
			}
		}
	}
	
	@Override
	public Map<ProductType, Long> getProductSuppliesInfo(final Long vmId) {
		final List<Product> products = productRepository.findByVendingMachineId(vmId);
		return products.stream()
				.collect(Collectors.groupingBy(Product::getType, Collectors.counting()));
	}
	
	@Override
	public VendingMachine addNewVm(final VendingMachine vendingMachine) {
		return vendingMachineRepository.save(vendingMachine);
	}
}
