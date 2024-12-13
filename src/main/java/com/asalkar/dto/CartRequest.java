package com.asalkar.dto;

import com.asalkar.oils.model.ProductVariant;

public class CartRequest {
    
	ProductVariant productvariant;
	String email;
	Integer volume;
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	public ProductVariant getProductvariant() {
		return productvariant;
	}
	public void setProductvariant(ProductVariant productvariant) {
		this.productvariant = productvariant;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
