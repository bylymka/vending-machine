package com.imperva.vendingmachine.model;

import lombok.Getter;

@Getter
public enum VendingMachineType {
	SMALL(20), MEDIUM(40), BIG(70);
	
	private int capacity;
	
	VendingMachineType(final int capacity) {
		this.capacity = capacity;
	}
}
