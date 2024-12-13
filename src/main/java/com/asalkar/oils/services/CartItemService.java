package com.asalkar.oils.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asalkar.dto.CartRequest;
import com.asalkar.oils.model.CartItem;
import com.asalkar.oils.repository.CartItemRepo;
import com.asalkar.oils.repository.ProductVariantRepo;
import com.asalkar.oils.repository.UserRepository;

@Service
public class CartItemService {

	@Autowired
	public CartItemRepo cartitemrepo;
	
	@Autowired
	public ProductVariantRepo productvariantrepo;
	
	public UserRepository userrepo;
	
	public void  AddTocart(Long userId,CartRequest productvariant) {
		
		
		
        	
        	CartItem cartitem=new CartItem();
        	
        	cartitem.setUserId(userId);
        	cartitem.setVariantId(productvariant.getProductvariant().getVariantId());
        	cartitem.setPrice(productvariant.getProductvariant().getPrice());
        	cartitem.setDescription(productvariant.getProductvariant().getDescription());
        	cartitem.setImage(productvariant.getProductvariant().getImage());
        	cartitem.setQuantity(productvariant.getProductvariant().getQuantity());
        	cartitem.setStockQuantity(productvariant.getProductvariant().getStockQuantity());
        	cartitem.setVolume(productvariant.getVolume());
        	cartitemrepo.save(cartitem);
        	
        	
        	
        	
        
		
		
		
	}

	public List<CartItem> getCartItemsByUserId(Long id) {
		// TODO Auto-generated method stub
		return  cartitemrepo.getCartItemsByUserId(id);
	}

	public void updateCartQuantity(Long cartId, int volume) {
		Optional<CartItem> cartitem=cartitemrepo.findById(cartId);
		System.out.println(cartitem.get());
		CartItem cartitem2=cartitem.get();
		System.out.println(cartitem2);
		cartitem2.setVolume(cartitem2.getVolume()+volume);
		cartitemrepo.save(cartitem2);
		System.out.println(cartitem2);
		
		
	}
}
