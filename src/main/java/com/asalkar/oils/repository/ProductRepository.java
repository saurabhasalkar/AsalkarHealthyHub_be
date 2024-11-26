package com.asalkar.oils.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asalkar.oils.model.Product;
import com.asalkar.oils.model.ProductVariant;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	
}

