package com.asalkar.oils.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.asalkar.dto.StockUpdateRequest;
import com.asalkar.oils.model.Product;
import com.asalkar.oils.model.ProductVariant;


public interface ProductService {
	
	public List<Product> findAllProducts();
	
	public List<ProductVariant> findProductVariantsbyId(int productid);
	
	public ResponseEntity<String> UpdateStockById(Integer variantId,Integer stockQuantity);
	
	public StockUpdateRequest CheckStock(Integer variantId);
    	
	

}
