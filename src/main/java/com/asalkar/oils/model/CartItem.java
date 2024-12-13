package com.asalkar.oils.model;
import java.math.BigDecimal;
import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Integer variantId;
    @Lob
    private byte[] image;
    private String description;
    private BigDecimal price;
    private Integer Volume;
    public Integer getVolume() {
		return Volume;
	}
	public void setVolume(Integer volume) {
		Volume = volume;
	}
	private Integer stockQuantity;
    public Integer getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	private String quantity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getVariantId() {
		return variantId;
	}
	public void setVariantId(Integer integer) {
		this.variantId = integer;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] bs) {
		this.image = bs;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", userId=" + userId + ", variantId=" + variantId + ", image="
				+ Arrays.toString(image).indexOf(4) + ", description=" + description + ", price=" + price + ", Volume=" + Volume
				+ ", stockQuantity=" + stockQuantity + ", quantity=" + quantity + "]";
	}
	

    // Getters and setters
}
