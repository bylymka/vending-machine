package com.imperva.vendingmachine.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Positive;

import com.imperva.vendingmachine.model.Status;
import com.imperva.vendingmachine.model.VendingMachineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendingMachineDto {
	private Long id;
	private Status status;
	private String address;
	private VendingMachineType type;
	
	@Positive
	private BigDecimal balance;
	private int capacity;
	private int size;
}
