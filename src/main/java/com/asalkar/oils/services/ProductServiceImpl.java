package com.asalkar.oils.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.asalkar.dto.StockUpdateRequest;
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

	@Override
	public ResponseEntity<String> UpdateStockById(Integer variantId, Integer stockQuantity) {
		
		Optional<ProductVariant> optionalProductVariant = productVariantRepo.findByVariantId(variantId);

	    
	    ProductVariant productVariant = null;
		try {
			productVariant = optionalProductVariant
			        .orElseThrow(() -> new Exception("Product variant not found"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	    if(productVariant.getStockQuantity()>=stockQuantity) {
	    productVariant.setStockQuantity(productVariant.getStockQuantity()-stockQuantity);
	    
          productVariantRepo.save(productVariant);
		
	    return ResponseEntity.ok("Stock Updated");
	    }
	    else {
	    	return ResponseEntity.ok("Stock Unavailable");
	    	
	    }

	   
	    
		
	}

	@Override
	public StockUpdateRequest CheckStock(Integer variantId) {
		// TODO Auto-generated method stub
		StockUpdateRequest stockupdaterequest =new StockUpdateRequest() ;
		ProductVariant productvariant=productVariantRepo.getVariantByVariantId(variantId);
		System.out.println("Output from db:"+ productvariant.toString());

		if(productvariant!=null) {
		
		stockupdaterequest.setStockQuantity(productvariant.getStockQuantity());
		return stockupdaterequest;
		}
		return stockupdaterequest;
	}

}
