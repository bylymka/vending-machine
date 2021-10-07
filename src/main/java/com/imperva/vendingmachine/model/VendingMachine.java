package com.imperva.vendingmachine.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="vending_machines")
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class VendingMachine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Status status;
	private BigDecimal balance;
	private String address;
	private VendingMachineType type;
	
	@OneToMany(mappedBy = "vendingMachine", fetch = FetchType.LAZY)
	private List<Product> products;
}
