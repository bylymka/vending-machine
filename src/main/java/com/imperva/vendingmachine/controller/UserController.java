package com.imperva.vendingmachine.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imperva.vendingmachine.dto.ProductResponseDto;
import com.imperva.vendingmachine.service.VendingMachineService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/user")
public class UserController {
	
	private final VendingMachineService vendingMachineService;
	
	@Autowired
	public UserController(final VendingMachineService vendingMachineService) {
		this.vendingMachineService = vendingMachineService;
	}
	
	
	@PutMapping("/vending-machine/{vmId}/products/{productTypeId}/buy")
	public ResponseEntity<ProductResponseDto> buyProduct(@PathVariable Long vmId, @PathVariable Long productTypeId, BigDecimal money) {
		ProductResponseDto product = vendingMachineService.buyProduct(vmId, productTypeId, money);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
}
