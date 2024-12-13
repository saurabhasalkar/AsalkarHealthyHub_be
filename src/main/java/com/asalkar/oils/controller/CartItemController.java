package com.asalkar.oils.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asalkar.dto.CartRequest;
import com.asalkar.dto.UpdateCartRequest;
import com.asalkar.dto.filter.JWTService;
//import com.asalkar.dto.utility.JwtUtils;
import com.asalkar.oils.model.CartItem;
import com.asalkar.oils.model.User;
import com.asalkar.oils.repository.ProductVariantRepo;
import com.asalkar.oils.services.CartItemService;
import com.asalkar.oils.services.UserService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CartItemController {
	
	@Autowired
	public CartItemService cartitemservice;
	
	@Autowired
	public JWTService jwtUtils;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public ProductVariantRepo productVariantRepo;

	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/api/cart/add")
	public ResponseEntity<?> addToCart(@RequestBody CartRequest cartRequest) {
	    try {
	        // Retrieve username from SecurityContext
	      //  String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      //  System.out.println("Username from SecurityContext: " + userEmail);

	        User user = userService.FindByEmail(cartRequest.getEmail());
	        if (user == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
	        }

	        // Fetch variant details
			/*
			 * ProductVariant variant =
			 * productVariantRepo.findByvariantId(cartRequest.getVariantId()); if (variant
			 * == null) { return
			 * ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product variant not found"
			 * ); }
			 */

	        // Add item to cart
	        cartitemservice.AddTocart(user.getId(),cartRequest);

	        return ResponseEntity.ok("Item added to cart");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add item to cart");
	    }
	}

	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/api/cart")
	public ResponseEntity<List<CartItem>> getCartItems(@RequestParam String userEmail) {
	    try {
	       // String userEmail = jwtUtils.extractUserName(token);

	        System.out.print(userEmail);
	        User user = userService.FindByEmail(userEmail);

	        if (user == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	        }

	        List<CartItem> cartItems = cartitemservice.getCartItemsByUserId(user.getId());
	        return ResponseEntity.ok(cartItems);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	
	@PostMapping("/api/cart/update")
	public ResponseEntity<?> updateCartQuantity(
	        @RequestBody UpdateCartRequest request
	        ) {
		
		System.out.println(request);
	    //String userEmail = userDetails.getUsername(); // Extract user email from the token
		cartitemservice.updateCartQuantity(request.getcartId(), request.getvolume());
	    return ResponseEntity.ok("Quantity updated successfully");
	}



	

}
