package com.asalkar.oils.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asalkar.oils.model.Product;
import com.asalkar.oils.model.ProductVariant;
import com.asalkar.oils.repository.ProductRepository;
import com.asalkar.oils.repository.ProductVariantRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	public ProductRepository productRepository;
	
	@Autowired
	public ProductVariantRepo productVariantRepo;

	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
	 	List<Product> productlist=productRepository.findAll();
	 	return productlist;
	}

	@Override
	public List<ProductVariant> findProductVariantsbyId(int productid) {
		// TODO Auto-generated method stub
		List<ProductVariant> productvariant=productVariantRepo.findByProductId(productid);
		return productvariant;
	}

}
