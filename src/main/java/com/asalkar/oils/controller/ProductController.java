package com.asalkar.oils.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.asalkar.oils.model.Product;
import com.asalkar.oils.model.ProductVariant;
import com.asalkar.oils.services.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productservice;

    // Endpoint to fetch all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return  productservice.findAllProducts();
    }
    
    @GetMapping("/productvariants/{productid}")
    public List<ProductVariant> getProductVariantsbyId(@PathVariable("productid") Integer productid)
    {
    	List<ProductVariant> variants=productservice.findProductVariantsbyId(productid);
    	return variants;
    }
}