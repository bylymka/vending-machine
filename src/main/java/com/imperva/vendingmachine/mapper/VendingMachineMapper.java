package com.imperva.vendingmachine.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperva.vendingmachine.dto.VendingMachineDto;
import com.imperva.vendingmachine.model.VendingMachine;
import com.imperva.vendingmachine.repository.ProductRepository;

@Service
public class VendingMachineMapper {
	
	private ProductRepository productRepository;
	
	@Autowired
	public VendingMachineMapper(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public VendingMachine toDomainObject(VendingMachineDto dto) {
		return VendingMachine.builder()
				.id(dto.getId())
				.status(dto.getStatus())
				.balance(dto.getBalance())
				.address(dto.getAddress())
				.type(dto.getType())
				.build();
	}
	
	public VendingMachineDto toDto (VendingMachine vendingMachine) {
		return VendingMachineDto.builder()
				.id(vendingMachine.getId())
				.status(vendingMachine.getStatus())
				.address(vendingMachine.getAddress())
				.type(vendingMachine.getType())
				.balance(vendingMachine.getBalance())
				.capacity(vendingMachine.getType().getCapacity())
				.size(productRepository.findByVendingMachineId(vendingMachine.getId()).size())
				.build();
	}
}
