package com.imperva.vendingmachine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imperva.vendingmachine.model.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
	ProductType findByName(String name);
}
