package com.imperva.vendingmachine.dto;

import javax.validation.constraints.Positive;

import com.imperva.vendingmachine.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSuppliesDto {
	private Product product;
	
	@Positive
	private Integer quantity;
}
