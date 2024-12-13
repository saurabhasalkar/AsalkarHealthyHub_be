package com.asalkar.oils.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asalkar.dto.StockUpdateRequest;
import com.asalkar.oils.model.ProductVariant;

public interface ProductVariantRepo  extends JpaRepository<ProductVariant, Integer> {

	List<ProductVariant> findByProductId(int productid);

	Optional<ProductVariant> findByVariantId(Integer variantId);

	ProductVariant getVariantByVariantId(Integer variantId);
	
	ProductVariant findByvariantId(Integer variantid);
	

}
