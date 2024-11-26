package com.asalkar.oils.services;

import java.util.List;

import com.asalkar.oils.model.Product;
import com.asalkar.oils.model.ProductVariant;


public interface ProductService {
	
	public List<Product> findAllProducts();
	
	public List<ProductVariant> findProductVariantsbyId(int productid);
    	
	

}
