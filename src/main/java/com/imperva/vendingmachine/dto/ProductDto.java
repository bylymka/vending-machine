package com.imperva.vendingmachine.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
	
	private String name;
	
	@Positive
	private BigDecimal price;
	
	private Long vendingMachineId;
}
