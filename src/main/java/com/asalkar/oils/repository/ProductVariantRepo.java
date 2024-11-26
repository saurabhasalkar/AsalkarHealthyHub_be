package com.asalkar.oils.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asalkar.oils.model.ProductVariant;

public interface ProductVariantRepo  extends JpaRepository<ProductVariant, Integer> {

	List<ProductVariant> findByProductId(int productid);
	

}
