package com.asalkar.oils.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asalkar.oils.model.CartItem;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {

	Optional<CartItem> findByUserIdAndVariantId(Long userId, int variantId);

	List<CartItem> getCartItemsByUserId(Long id);

	     
	
}
