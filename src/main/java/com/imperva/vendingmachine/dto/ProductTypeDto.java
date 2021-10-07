package com.imperva.vendingmachine.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypeDto {
	private String name;
	
	@Positive
	private BigDecimal price;
}
