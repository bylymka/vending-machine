package com.imperva.vendingmachine.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imperva.vendingmachine.dto.ProductSuppliesDto;
import com.imperva.vendingmachine.dto.ProductTypeDto;
import com.imperva.vendingmachine.dto.VendingMachineDto;
import com.imperva.vendingmachine.mapper.ProductTypeMapper;
import com.imperva.vendingmachine.mapper.VendingMachineMapper;
import com.imperva.vendingmachine.model.ProductType;
import com.imperva.vendingmachine.model.VendingMachine;
import com.imperva.vendingmachine.service.ProductTypeService;
import com.imperva.vendingmachine.service.VendingMachineService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/admin")
public class AdminController {
	
	private final VendingMachineService vendingMachineService;
	private final VendingMachineMapper vendingMachineMapper;
	private final ProductTypeService productTypeService;
	private final ProductTypeMapper productTypeMapper;
	
	@Autowired
	public AdminController(final VendingMachineService vendingMachineService,
						   VendingMachineMapper vendingMachineMapper,
						   ProductTypeService productTypeService,
						   ProductTypeMapper productTypeMapper) {
		this.vendingMachineService = vendingMachineService;
		this.vendingMachineMapper = vendingMachineMapper;
		this.productTypeService = productTypeService;
		this.productTypeMapper = productTypeMapper;
	}
	
	@PostMapping
	public ResponseEntity<VendingMachineDto> createVendingMachine(@RequestBody @Valid VendingMachineDto vmDto) {
		VendingMachine vm = vendingMachineService
				.addNewVm(vendingMachineMapper.toDomainObject(vmDto));
		return new ResponseEntity<>(vendingMachineMapper.toDto(vm), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/vending-machine/{vmId}")
	public ResponseEntity<VendingMachineDto> openVendingMachine(@PathVariable Long vmId) {
		VendingMachine vm = vendingMachineService.getById(vmId);
		return new ResponseEntity<>(vendingMachineMapper.toDto(vm), HttpStatus.OK);
	}
	
	@PutMapping("/vending-machine/{vmId}/money/take")
	public ResponseEntity<VendingMachineDto> takeMoney(@PathVariable Long vmId) {
		VendingMachine vm = vendingMachineService.takeMoney(vmId);
		return new ResponseEntity<>(vendingMachineMapper.toDto(vm), HttpStatus.OK);
	}
	
	@PostMapping("/vending-machine/{vmId}/products")
	public ResponseEntity<Void> resupply(@RequestBody @Valid final ProductSuppliesDto products, @PathVariable Long vmId) {
		vendingMachineService.resupply(products, vmId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/products")
	public ResponseEntity<ProductTypeDto> addProductType(@RequestBody @Valid ProductTypeDto productTypeDto) {
		ProductType productType = productTypeService.addProductType(productTypeDto);
		return new ResponseEntity<>(productTypeMapper.toDto(productType), HttpStatus.CREATED);
	}
	
	@PutMapping("/products/{productTypeId}")
	public ResponseEntity<ProductTypeDto> updateProductPrice(@RequestParam("newPrice") BigDecimal newPrice, @PathVariable Long productTypeId) {
		ProductType productType = productTypeService.changePrice(productTypeId, newPrice);
		return new ResponseEntity<>(productTypeMapper.toDto(productType), HttpStatus.OK);
	}
}
