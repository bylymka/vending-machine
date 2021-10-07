package com.imperva.vendingmachine.dto;

import java.math.BigDecimal;

import com.imperva.vendingmachine.model.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ProductResponseDto {
	private Product product;
	private BigDecimal change;
}
