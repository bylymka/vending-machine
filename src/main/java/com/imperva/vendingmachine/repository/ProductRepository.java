package com.imperva.vendingmachine.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imperva.vendingmachine.model.Product;
import com.imperva.vendingmachine.model.ProductType;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	LinkedList<Product> findByTypeIdAndVendingMachineId(Long typeId, Long vendingMachineId);
	//boolean existsByTypeAndVendingMachineId(ProductType type, Long vendingMachineId);
	List<Product> findByVendingMachineId(Long vendingMachineId);
}
