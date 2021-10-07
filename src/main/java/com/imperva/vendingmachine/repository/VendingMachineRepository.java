package com.imperva.vendingmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.imperva.vendingmachine.model.VendingMachine;

@Repository
public interface VendingMachineRepository extends JpaRepository<VendingMachine, Long> {
	VendingMachine findByAddressContains(String address);
}
