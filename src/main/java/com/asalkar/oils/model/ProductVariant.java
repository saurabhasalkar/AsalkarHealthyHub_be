package com.asalkar.oils.model;

import java.math.BigDecimal;
import java.util.Arrays;

import jakarta.persistence.*;

@Entity
@Table(name = "product_variants")
public class ProductVariant {

 

	@Override
	public String toString() {
		return "ProductVariant [variantId=" + variantId + ", product=" + product + ", quantity=" + quantity + ", price="
				+ price + ", stockQuantity=" + stockQuantity + ", image=" + Arrays.toString(image) + ", description="
				+ description + "]";
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id")
    private Integer variantId;

    @ManyToOne(fetch = FetchType.EAGER) // Many variants can belong to one product
    @JoinColumn(name = "product_id", nullable = false) // FK to the `products` table
    private Product product;

    @Column(name = "quantity", nullable = false, length = 20)
    private String quantity;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;
    
    @Lob // Specifies that the field should be treated as a large object
    private byte[] image;
    
    private String description;
    
    public byte[] getImage() {
 		return image;
 	}

 	public void setImage(byte[] image) {
 		this.image = image;
 	}

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// Getters and Setters
    public Integer getVariantId() {
        return variantId;
    }

    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

   
    public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
