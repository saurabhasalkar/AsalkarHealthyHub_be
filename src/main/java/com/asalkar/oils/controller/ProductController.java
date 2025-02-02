package com.asalkar.oils.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asalkar.dto.StockUpdateRequest;
import com.asalkar.oils.model.Product;
import com.asalkar.oils.model.ProductVariant;
import com.asalkar.oils.services.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {

    @Autowired
    private ProductService productservice;

    // Endpoint to fetch all products
    @CrossOrigin(origins = "http://localhost:3000")
    @Cacheable
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return  productservice.findAllProducts();
    }
    
    @Cacheable
    @GetMapping("/productvariants/{productid}")
    public List<ProductVariant> getProductVariantsbyId(@PathVariable("productid") Integer productid)
    {
    	List<ProductVariant> variants=productservice.findProductVariantsbyId(productid);
    	return variants;
    }
    
    @PostMapping("/updateStock/{variantId}")
    public ResponseEntity<String> updateStock(@PathVariable("variantId") Integer variantId,@RequestBody StockUpdateRequest stockUpdateRequest )
    {
    	ResponseEntity<String> updatedStock= productservice.UpdateStockById(variantId, stockUpdateRequest.getStockQuantity());
    	return updatedStock;
    }
    
    @GetMapping("/checkStock/{variantId}")
    public StockUpdateRequest checkStock(@PathVariable("variantId")Integer variantId) {
    	System.out.println("In checkstock controller");
    	return productservice.CheckStock(variantId);
    }
}